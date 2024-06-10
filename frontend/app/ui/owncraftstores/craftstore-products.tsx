import {fetchCraftstoreProductsById} from "@/app/lib/data";
import {Product} from "@/app/lib/definitions";
import {ShoppingBagIcon} from "@heroicons/react/24/outline";
import {
    ShowCraftstoreComments,
    ShowCraftstoreProducts,
    DeleteProduct,
    UpdateProduct,
    CreateProduct
} from "@/app/ui/dashcraftsman/buttons-craftsman";
import {formatCurrency} from "@/app/lib/utils";

export default async function CraftstoreProducts({
                                                     id,
                                                 }: {
    id: number;
}) {
    const sampler = await fetchCraftstoreProductsById(id);
    const prodList = sampler.productList;

    return (
        <>
        <div className="mt-5 flex w-full justify-center">
            <CreateProduct id={id} samplerId={sampler.id} />
        </div>
        <div className="mt-6 flow-root">
            <div className="inline-block min-w-full align-middle">
                <div className="rounded-lg bg-gray-50 p-2 md:pt-0">
                    <div className="md:hidden">
                        {prodList.map((product:Product) => (
                            <div
                                key={product.id}
                                className="mb-2 w-full rounded-md bg-white p-4"
                            >
                                <div className="flex items-center justify-between border-b pb-4">
                                    <div>
                                        <div className="mb-2 flex items-center">
                                            <ShoppingBagIcon className="h-12 w-12"/>
                                            <p className="truncate text-sm">{product.description}</p>
                                        </div>
                                        <p className="text-xl font-medium">
                                            {formatCurrency(product.price)}
                                        </p>
                                        <div className="flex justify-end gap-2">
                                            <UpdateProduct id={id} productId={product.id} />
                                            <DeleteProduct id={id} productId={product.id} />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                    <table className="hidden min-w-full text-gray-900 md:table">
                        <thead className="rounded-lg text-left text-sm font-normal">
                        <tr>
                            <th scope="col" className="px-4 py-5 font-medium sm:pl-6">
                                Description
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Price
                            </th>
                            <th scope="col" className="px-3 py-5 font-medium">
                                Edit/Delete
                            </th>
                        </tr>
                        </thead>
                        <tbody className="bg-white">
                        {prodList.map((product:Product) => (
                            <tr
                                key={product.id}
                                className="w-full border-b py-3 text-sm last-of-type:border-none [&:first-child>td:first-child]:rounded-tl-lg [&:first-child>td:last-child]:rounded-tr-lg [&:last-child>td:first-child]:rounded-bl-lg [&:last-child>td:last-child]:rounded-br-lg"
                            >
                                <td className="whitespace-nowrap py-3 pl-6 pr-3">
                                    <div className="flex items-center gap-3">
                                        <ShoppingBagIcon className="h-12 w-12"/>
                                        <p className="truncate text-sm">{product.description}</p>
                                    </div>
                                </td>
                                <td className="whitespace-nowrap px-3 py-3">
                                    {formatCurrency(product.price)}
                                </td>
                                <td className="whitespace-nowrap py-3 pl-6 pr-3">
                                    <div className="flex justify-left gap-3">
                                        <UpdateProduct id={id} productId={product.id} />
                                        <DeleteProduct id={id} productId={product.id} />
                                    </div>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
                <div className="flex justify-center">
                    <ShowCraftstoreComments id={id} />
                    <ShowCraftstoreProducts id={id} />
                </div>
            </div>
        </div>
        </>
    );
}