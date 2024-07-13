import Breadcrumbs from '@/app/ui/breadcrumbs';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import CraftstoreProducts from "@/app/ui/owncraftstores/craftstore-products";
import {auth} from "@/auth";
import { Metadata } from 'next';

export const metadata: Metadata = {
    title: 'Store Products',
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
                        label: 'Craftstore Products',
                        href: `/dashcraftsman/craftstores/${id}/products`,
                        active: true,
                    },
                ]}
            />

            <Suspense key={id} fallback={<Skeleton/>}>
                <CraftstoreProducts id={id}/>
            </Suspense>

        </main>
    );
}