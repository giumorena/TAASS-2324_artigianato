import {fetchCraftstoreCommentsById} from "@/app/lib/data";
import {Comment} from "@/app/lib/definitions";
import clsx from "clsx";
import {UserCircleIcon} from "@heroicons/react/24/outline";
import {lusitana} from "@/app/ui/fonts";
import {ShowCraftstoreComments, ShowCraftstoreInfo, ShowCraftstoreProducts} from "@/app/ui/dashguest/buttons-guest";

export default async function CraftstoreComments({
                                                     id,
                                                 }: {
    id: number;
}) {
    const comList = await fetchCraftstoreCommentsById(id);

    return (
        <div className="flex grow flex-col justify-between rounded-xl bg-gray-50 p-4">
            <div className="bg-white px-6">
                {comList.map((comment:Comment, i:number) => {
                    return (
                        <div
                            key={comment.id}
                            className={clsx(
                                'flex flex-row items-center justify-between py-4',
                                {
                                    'border-t': i !== 0,
                                },
                            )}
                        >
                            <div className="flex items-center">
                                <UserCircleIcon className="h-7 w-7"/>
                                <div className="min-w-0">
                                    <p className="text-sm italic md:text-base">
                                        {comment.userName}
                                    </p>
                                    <p className="text-sm font-semibold md:text-base">
                                        {comment.text}
                                    </p>
                                </div>
                            </div>
                        </div>
                    );
                })}
                <div className="flex justify-center">
                    <ShowCraftstoreInfo id={id} />
                    <ShowCraftstoreProducts id={id} />
                    <ShowCraftstoreComments id={id} />
                </div>
            </div>
        </div>
    );
}