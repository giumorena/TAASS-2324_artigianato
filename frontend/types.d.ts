import NextAuth, { type DefaultSession } from "next-auth"

declare module "next-auth" {
    /**
     * The shape of the user object returned in the OAuth providers' `profile` callback,
     * or the second parameter of the `session` callback, when using a database.
     */
    interface User {
        name: string
        email: string
        /** The person category (craftsman or user). */
        category: 'craftsman'|'user'
        /** The id on the service database. */
        dbId: number|undefined
    }

    /**
     * Returned by `auth`, `useSession`, `getSession` and received as a prop on the `SessionProvider` React Context
     */
    interface Session {
        user: {
            /** The person category (craftsman or user). */
            category: 'craftsman'|'user'
            /** The id on the service database. */
            dbId: number|undefined

            /**
             * By default, TypeScript merges new interface properties and overwrites existing ones.
             * In this case, the default session user properties will be overwritten,
             * with the new ones defined above. To keep the default session user properties,
             * you need to add them back into the newly declared interface.
             */
        } & DefaultSession["user"]
    }
}

// The `JWT` interface can be found in the `next-auth/jwt` submodule
import { JWT } from "next-auth/jwt"

declare module "next-auth/jwt" {
    /** Returned by the `jwt` callback and `auth`, when using JWT sessions */
    interface JWT {
        /** The person category (craftsman or user). */
        category: 'craftsman'|'user'
        /** The id on the service database. */
        dbId: number|undefined
        /** OpenID ID Token */
        id_token?: string
    }
}