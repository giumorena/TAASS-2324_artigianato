import Form from '@/app/ui/invoices/edit-form';
import Breadcrumbs from '@/app/ui/breadcrumbs';
import { fetchCustomers } from '@/app/lib/data';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import CraftstoreComments from "@/app/ui/dashguest/craftstore-comments";

export default async function Page({ params }: { params: { id: number } }) {
    const id = params.id;
    return (
        <main>
            <Breadcrumbs
                breadcrumbs={[
                    { label: 'Search', href: '/dashguest/search' },
                    {
                        label: 'Craftstore-related Comments',
                        href: `/dashguest/search/${id}/comments`,
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