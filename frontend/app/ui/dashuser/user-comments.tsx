import {fetchUserCommentsById} from "@/app/lib/data";
import {UserComment} from "@/app/lib/definitions";
import {formatDateToLocal} from "@/app/lib/utils";

export default async function UserComments({
                                                   userId,
                                               }: {
    userId: number;
}) {
    const comList = await fetchUserCommentsById(userId);

    return (
        <div className="mt-6 flow-root">
            <div className="inline-block min-w-full align-middle">
                <div className="rounded-lg bg-gray-50 p-2 md:pt-0">
                    <div className="md:hidden">
                        {comList.map((comment:UserComment) => (
                            <div
                                key={comment.id}
                                className="mb-2 w-full rounded-md bg-white p-4"
                            >
                                <div className="flex items-center justify-between border-b pb-4">
                                    <div>
                                        <p>{comment.craftstoreName}</p>
                                        <p className="truncate text-sm italic text-gray-500">{comment.text}</p>
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
                                Craftstore
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
                        {comList.map((comment:UserComment) => (
                            <tr
                                key={comment.id}
                                className="w-full border-b py-3 text-sm last-of-type:border-none [&:first-child>td:first-child]:rounded-tl-lg [&:first-child>td:last-child]:rounded-tr-lg [&:last-child>td:first-child]:rounded-bl-lg [&:last-child>td:last-child]:rounded-br-lg"
                            >
                                <td className="whitespace-nowrap py-3 pl-6 pr-3">
                                    <p>{comment.craftstoreName}</p>
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
            </div>
        </div>
    );
}