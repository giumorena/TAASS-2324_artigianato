import Form from '@/app/ui/invoices/edit-form';
import Breadcrumbs from '@/app/ui/breadcrumbs';
import { fetchCustomers } from '@/app/lib/data';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import {lusitana} from "@/app/ui/fonts";
import CraftstoreInfo from "@/app/ui/craftstores/craftstore-info";
import { Metadata } from 'next';

export const metadata: Metadata = {
    title: 'Searched Store Info',
};

export default async function Page({ params }: { params: { id: number } }) {
    const id = params.id;
    return (
        <main>
            <Breadcrumbs
                breadcrumbs={[
                    { label: 'Search', href: '/dashguest/search' },
                    {
                        label: 'Craftstore Info',
                        href: `/dashguest/search/${id}/info`,
                        active: true,
                    },
                ]}
            />
            <div className="w-full">
                <div className="flex w-full items-center justify-between">
                    <h1 className={`${lusitana.className} text-xl`}>Craftstore Info</h1>
                </div>

                <Suspense key={id} fallback={<Skeleton/>}>
                    <CraftstoreInfo id={id}/>
                </Suspense>
            </div>

        </main>
    );
}