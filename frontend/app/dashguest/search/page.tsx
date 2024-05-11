import { lusitana } from '@/app/ui/fonts';
import CraftstoreSearch from "@/app/ui/dashguest/craftstore-search";
import CraftstoresTable from "@/app/ui/dashguest/craftstores-table";
import Skeleton from "@/app/ui/skeletons";
import { Suspense } from 'react';
import {fetchCraftstoresPages} from "@/app/lib/data";
import Pagination from "@/app/ui/pagination";

export default async function Page({
                                       searchParams,
                                   }: {
    searchParams?: {
        query?: string;
        page?: string;
    };
}) {
    //const query = searchParams?.query || '';
    //const currentPage = Number(searchParams?.page) || 1;
    const sparams=new URLSearchParams(searchParams);

    if (!sparams.has('page')){
        sparams.set('page','1');
    }

    //items per page
    sparams.set('size','1');

    const query = sparams.toString() || '';

    const totalPages = await fetchCraftstoresPages(query);

    console.log("searchparams="+ JSON.stringify(searchParams));
    console.log("query=" + query);
    console.log("totalPages=" + totalPages);

    return (
        <div className="w-full">
            <div className="flex w-full items-center justify-between">
                <h1 className={`${lusitana.className} text-xl`}>Search</h1>
            </div>
            <div className="mt-4 flex items-center justify-between gap-2 md:mt-8">
                <CraftstoreSearch/>
            </div>

            <Suspense key={query} fallback={<Skeleton/>}>
                <CraftstoresTable query={query}/>
            </Suspense>

            <div className="mt-5 flex w-full justify-center">
                <Pagination totalPages={totalPages}/>
            </div>

        </div>
    );
}

export const dynamic = "force-dynamic";