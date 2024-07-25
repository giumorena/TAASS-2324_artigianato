import NextAuth from "next-auth"
import Keycloak from "next-auth/providers/keycloak"
import { type JWT } from "next-auth/jwt"
import {addUser, fetchCraftsmanByEmail, fetchUserByEmail} from "@/app/lib/data";




export const { handlers, signIn, signOut, auth } = NextAuth({
    pages: {
        signIn: '/login',
    },
    providers: [
        Keycloak({
            profile(profile) {
                console.log('profile:' + JSON.stringify(profile));
                return { name: profile.name,
                    email: profile.email,
                    category: profile.category, //exposes the category attribute on user object
                    dbId : undefined, //will be determined in the signIn callback
                }
            },
        })
    ],
    callbacks: {
        authorized: async ({ auth, request: { nextUrl } }) => {

            // !!auth?.user is equivalent to auth?.user !== null, so the person logged in
            const isCraftsman = !!auth?.user && auth?.user.category === 'craftsman';
            const isUser = !!auth?.user && auth?.user.category === 'user';
            const isOnDashCraftsman = nextUrl.pathname.startsWith('/dashcraftsman');
            const isOnDashUser = nextUrl.pathname.startsWith('/dashuser');

            if (isOnDashCraftsman) {
                if (isCraftsman) return true;
                return false; // Redirect unauthenticated users to login page
            }
            else if (isOnDashUser) {
                if (isUser) return true;
                return false; // Redirect unauthenticated users to login page
            }
            else if (isCraftsman) {
                console.log(JSON.stringify(auth?.user));
                return Response.redirect(new URL('/dashcraftsman/craftstores',nextUrl));
            }
            else if (isUser) {
                console.log(JSON.stringify(auth?.user));
                return Response.redirect(new URL('/dashuser/comments',nextUrl));
            }

            return true;
        },
        async signIn({ user }) {
            console.log('callback signIn, user:' + JSON.stringify(user));
            if(user && user.category && user.email && user.name){
                //Craftsman is assumed to be in the craftsman-service database
                if(user.category === 'craftsman'){
                    const craftsman = await fetchCraftsmanByEmail(user.email);
                    user.dbId = craftsman.id;
                }
                //If the user is already in the user-service database,his id is retrieved
                // and added to the user's data so that it can then be made available in the session,
                // otherwise the user will be inserted into the database
                else if(user.category === 'user'){
                    let usr = await fetchUserByEmail(user.email);
                    if(usr.id) {
                        user.dbId = usr.id;
                    }
                    else{
                        const newUsr = {
                            name : user.name,
                            email : user.email,
                            commentList: [],
                        }
                        usr = await addUser(newUsr);
                        user.dbId = usr.id;
                    }
                }
                return true;
            }
            else
                return false;
        },
        jwt({ token, user, account }) {
            if(account) token.id_token = account.id_token as string//the id_token, used to log out from keycloak, is added to the JWT token
            if(user) {
                token.category = user.category; //persists the category by assigning it to token
                token.dbId = user.dbId; //persists the database id by assigning it to token
            }
            console.log('token: '+ JSON.stringify(token));
            return token
        },
        session({ session, token }) {
            session.user.category = token.category; //persists the category by assigning it to session
            session.user.dbId = token.dbId; //persists the database id by assigning it to session
            console.log('session: '+ JSON.stringify(session));
            return session
        },
    },

    events: {
        //Sent when the user signs out
        async signOut(message) {
            //check to prevent typescript error
            if("token" in message) {
                try {
                    //const { id_token } = token;
                    const logOutUrl = new URL(`${process.env.AUTH_KEYCLOAK_ISSUER}/protocol/openid-connect/logout`);
                    logOutUrl.searchParams.set("id_token_hint", message.token!.id_token!);
                    console.log(logOutUrl);

                    const res = await fetch(logOutUrl); //log out from keycloak

                } catch (error) {
                    console.log(error)
                    // This will activate the closest `error.js` Error Boundary
                    throw new Error('Failed to log out from Keycloack')
                }
            }
        },

    }
})