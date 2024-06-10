import Breadcrumbs from '@/app/ui/breadcrumbs';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import CraftstoreProducts from "@/app/ui/owncraftstores/craftstore-products";

export default async function Page({ params }: { params: { id: number } }) {
    const id = params.id;
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