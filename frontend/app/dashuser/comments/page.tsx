import {lusitana} from "@/app/ui/fonts";
import UserComments from "@/app/ui/dashuser/user-comments";
import { CreateComment } from '@/app/ui/dashuser/buttons-user';
import {Suspense} from "react";
import Skeleton from "@/app/ui/skeletons";


export default async function Page() {
    const userId = 1; //user id from login

    return (
        <div className="w-full">
            <div className="flex w-full items-center justify-between">
                <h1 className={`${lusitana.className} text-xl`}>Posted Comments</h1>
            </div>

            <div className="mt-5 flex w-full justify-center">
                <CreateComment />
            </div>

            <Suspense key={userId} fallback={<Skeleton/>}>
                <UserComments userId={userId}/>
            </Suspense>

        </div>
    );
}

export const dynamic = "force-dynamic";