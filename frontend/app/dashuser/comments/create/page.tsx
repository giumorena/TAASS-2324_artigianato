import Form from '@/app/ui/users/create-form';
import Breadcrumbs from '@/app/ui/breadcrumbs';
import { fetchSortedCraftstores } from '@/app/lib/data';

export default async function Page() {
    const craftstores = await fetchSortedCraftstores();

    const userId = 1; //user id from login
    const userName = 'Mario Rossi'; //username

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