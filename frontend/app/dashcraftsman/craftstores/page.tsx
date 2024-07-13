import {lusitana} from "@/app/ui/fonts";
import CraftsmanStores from "@/app/ui/dashcraftsman/craftsman-stores";
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";
import {auth} from "@/auth";
import { Metadata } from 'next';

export const metadata: Metadata = {
    title: 'Craftsman Stores',
};

export default async function Page() {

    const session = await auth();
    if (!session || !session.user || !session.user.dbId || session.user.category !== 'craftsman') return null;

    console.log('dashcraftsman session user: ' + JSON.stringify(session.user));

    const craftsmanId = session.user.dbId; //craftsman id from login

    return (
        <div className="w-full">
            <div className="flex w-full items-center justify-between">
                <h1 className={`${lusitana.className} text-xl`}>Owned Craftstores</h1>
            </div>

            <Suspense key={craftsmanId} fallback={<Skeleton/>}>
                <CraftsmanStores craftsmanId={craftsmanId}/>
            </Suspense>

        </div>
    );
}

export const dynamic = "force-dynamic";