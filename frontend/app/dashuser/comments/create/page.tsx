import Form from '@/app/ui/users/create-form';
import Breadcrumbs from '@/app/ui/breadcrumbs';
import {fetchSortedCraftstores, fetchUserByEmail} from '@/app/lib/data';
import {auth} from "@/auth";
import LoggedInfo from "@/app/ui/logged-info";

export default async function Page() {

    const session = await auth();
    if (!session || !session.user || !session.user.dbId || !session.user.name || session.user.category !== 'user') return null;

    const userId = session.user.dbId; //user id from login
    const userName = session.user.name; //username from login

    const craftstores = await fetchSortedCraftstores();

    return (
        <main>
            <Breadcrumbs
                breadcrumbs={[
                    { label: 'Comments', href: '/dashuser/comments' },
                    {
                        label: 'Create Comment',
                        href: '/dashuser/comments/create',
                        active: true,
                    },
                ]}
            />
            <Form craftstores={craftstores} userId={userId} userName={userName}/>
        </main>
    );
}