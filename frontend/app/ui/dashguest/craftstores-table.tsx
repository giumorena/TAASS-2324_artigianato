import {fetchFilteredPagedCraftstores} from "@/app/lib/data";
import InvoiceStatus from "@/app/ui/invoices/status";
import {formatCurrency, formatDateToLocal} from "@/app/lib/utils";
import {DeleteInvoice, UpdateInvoice} from "@/app/ui/invoices/buttons";
import {CraftstoresTable, Ownership} from "@/app/lib/definitions";
import {ShowCraftstoreInfo,ShowCraftstoreProducts,ShowCraftstoreComments} from "@/app/ui/dashguest/buttons-guest";
import OwnersList from "@/app/ui/dashguest/owners-list";

export default async function CraftstoresTable({
                                                query,
                                            }: {
    query: string;
}) {
    const craftstores = await fetchFilteredPagedCraftstores(query);

    return (
        <div className="mt-6 flow-root">
            <div className="inline-block min-w-full align-middle">
                <div className="rounded-lg bg-gray-50 p-2 md:pt-0">
                    <div className="md:hidden">
                        {craftstores?.map((craftstore:CraftstoresTable) => (
                            <div
                                key={craftstore.id}
                                className="mb-2 w-full rounded-md bg-white p-4"
                            >
                                <div className="flex items-center justify-between border-b pb-4">
                                    <div>
                                        <p className="text-sm text-gray-500">{craftstore.name}</p>
                                        <p className="text-sm text-gray-500">{craftstore.category}</p>
                                        <p className="text-sm text-gray-500">{craftstore.description}</p>
                                        <div className="relative text-sm text-gray-500" >
                                            <OwnersList owlist={craftstore.ownerList}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="flex w-full items-center justify-between pt-4">
                                    <div className="flex justify-end gap-2">
                                        <ShowCraftstoreInfo id={craftstore.id} />
                                        <ShowCraftstoreProducts id={craftstore.id} />
                                        <ShowCraftstoreComments id={craftstore.id} />
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                    <table className="hidden min-w-full text-gray-900 md:table">
                        <thead className="rounded-lg text-left text-sm font-normal">
                        <tr>
                            <th scope="col" className="px-4 py-5 font-medium sm:pl-6">
                                Name
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Category
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Description
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Owners
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Info/Products/Comments
                            </th>
                        </tr>
                        </thead>
                        <tbody className="bg-white">
                        {craftstores?.map((craftstore:CraftstoresTable) => (
                            <tr
                                key={craftstore.id}
                                className="w-full border-b py-3 text-sm last-of-type:border-none [&:first-child>td:first-child]:rounded-tl-lg [&:first-child>td:last-child]:rounded-tr-lg [&:last-child>td:first-child]:rounded-bl-lg [&:last-child>td:last-child]:rounded-br-lg"
                            >
                                <td className="whitespace-nowrap px-3 py-3">
                                    {craftstore.name}
                                </td>
                                <td className="whitespace-nowrap px-3 py-3">
                                    {craftstore.category}
                                </td>
                                <td className="whitespace-nowrap px-3 py-3">
                                    {craftstore.description}
                                </td>
                                <td className="whitespace-nowrap px-3 py-3">
                                    <OwnersList owlist={craftstore.ownerList}/>
                                </td>
                                <td className="whitespace-nowrap py-3 pl-6 pr-3">
                                    <div className="flex justify-left gap-3">
                                        <ShowCraftstoreInfo id={craftstore.id} />
                                        <ShowCraftstoreProducts id={craftstore.id} />
                                        <ShowCraftstoreComments id={craftstore.id} />
                                    </div>
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