import {fetchCraftstoreCommentsById} from "@/app/lib/data";
import {CraftstoreComment} from "@/app/lib/definitions";
import {UserCircleIcon} from "@heroicons/react/24/outline";
import {ShowCraftstoreComments, ShowCraftstoreInfo, ShowCraftstoreProducts} from "@/app/ui/dashguest/buttons-guest";
import {formatDateToLocal} from "@/app/lib/utils";

export default async function CraftstoreComments({
                                                     id,
                                                 }: {
    id: number;
}) {
    const comList = await fetchCraftstoreCommentsById(id);

    return (
        <div className="mt-6 flow-root">
            <div className="inline-block min-w-full align-middle">
                <div className="rounded-lg bg-gray-50 p-2 md:pt-0">
                    <div className="md:hidden">
                        {comList.map((comment:CraftstoreComment) => (
                            <div
                                key={comment.id}
                                className="mb-2 w-full rounded-md bg-white p-4"
                            >
                                <div className="flex items-center justify-between border-b pb-4">
                                    <div>
                                        <div className="mb-2 flex items-center">
                                            <UserCircleIcon className="h-8 w-8"/>
                                            <p>{comment.userName}</p>
                                        </div>
                                        <p className="truncate italic text-sm text-gray-500">{comment.text}</p>
                                        <p>{formatDateToLocal(comment.postDate)}</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                    <table className="hidden min-w-full text-gray-900 md:table">
                        <thead className="rounded-lg text-left text-sm font-normal">
                        <tr>
                            <th scope="col" className="px-4 py-5 font-medium sm:pl-6">
                                User
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Text
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Date
                            </th>
                        </tr>
                        </thead>
                        <tbody className="bg-white">
                        {comList.map((comment:CraftstoreComment) => (
                            <tr
                                key={comment.id}
                                className="w-full border-b py-3 text-sm last-of-type:border-none [&:first-child>td:first-child]:rounded-tl-lg [&:first-child>td:last-child]:rounded-tr-lg [&:last-child>td:first-child]:rounded-bl-lg [&:last-child>td:last-child]:rounded-br-lg"
                            >
                                <td className="whitespace-nowrap py-3 pl-6 pr-3">
                                    <div className="flex items-center gap-3">
                                        <UserCircleIcon className="h-8 w-8"/>
                                        <p>{comment.userName}</p>
                                    </div>
                                </td>
                                <td className="italic whitespace-nowrap px-3 py-3">
                                    {comment.text}
                                </td>
                                <td className="whitespace-nowrap px-3 py-3">
                                    {formatDateToLocal(comment.postDate)}
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
                <div className="flex justify-center">
                    <ShowCraftstoreInfo id={id} />
                    <ShowCraftstoreProducts id={id} />
                    <ShowCraftstoreComments id={id} />
                </div>
            </div>
        </div>
    );
}