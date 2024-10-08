import Breadcrumbs from '@/app/ui/breadcrumbs';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import CraftstoreProducts from "@/app/ui/craftstores/craftstore-products";
import { Metadata } from 'next';

export const metadata: Metadata = {
    title: 'Searched Store Products',
};

export default async function Page({ params }: { params: { id: number } }) {
    const id = params.id;
    return (
        <main>
            <Breadcrumbs
                breadcrumbs={[
                    { label: 'Search', href: '/dashguest/search' },
                    {
                        label: 'Craftstore Products',
                        href: `/dashguest/search/${id}/products`,
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