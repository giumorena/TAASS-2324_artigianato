import Breadcrumbs from '@/app/ui/breadcrumbs';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import CraftstoreComments from "@/app/ui/owncraftstores/craftstore-comments";
import {auth} from "@/auth";
import { Metadata } from 'next';

export const metadata: Metadata = {
    title: 'Store Comments',
};

export default async function Page({ params }: { params: { id: number } }) {
    const id = params.id;

    const session = await auth();
    if (!session || !session.user || session.user.category !== 'craftsman') return null;

    return (
        <main>
            <Breadcrumbs
                breadcrumbs={[
                    { label: 'Craftstores', href: '/dashcraftsman/craftstores' },
                    {
                        label: 'Craftstore-related Comments',
                        href: `/dashcraftsman/craftstores/${id}/comments`,
                        active: true,
                    },
                ]}
            />


            <Suspense key={id} fallback={<Skeleton/>}>
                <CraftstoreComments id={id}/>
            </Suspense>


        </main>
    );
}