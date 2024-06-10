import Breadcrumbs from '@/app/ui/breadcrumbs';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import CraftstoreComments from "@/app/ui/owncraftstores/craftstore-comments";

export default async function Page({ params }: { params: { id: number } }) {
    const id = params.id;
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