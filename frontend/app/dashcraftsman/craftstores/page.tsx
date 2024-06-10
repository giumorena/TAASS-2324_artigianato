import {lusitana} from "@/app/ui/fonts";
import CraftsmanStores from "@/app/ui/dashcraftsman/craftsman-stores";
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";

export default async function Page() {
    const craftsmanId = 1; //craftsman id from login

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