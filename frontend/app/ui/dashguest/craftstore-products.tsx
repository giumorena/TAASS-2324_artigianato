import {fetchCraftstoreProductsById} from "@/app/lib/data";
import {Product} from "@/app/lib/definitions";
import clsx from "clsx";
import {ShoppingBagIcon,CurrencyEuroIcon} from "@heroicons/react/24/outline";
import {lusitana} from "@/app/ui/fonts";
import {ShowCraftstoreComments, ShowCraftstoreInfo, ShowCraftstoreProducts} from "@/app/ui/dashguest/buttons-guest";

export default async function CraftstoreProducts({
                                                 id,
                                             }: {
    id: number;
}) {
    const sampler = await fetchCraftstoreProductsById(id);
    const prodList = sampler.productList;

    return (
        <div className="flex grow flex-col justify-between rounded-xl bg-gray-50 p-4">
            <div className="bg-white px-6">
                {prodList.map((product:Product, i:number) => {
                    return (
                        <div
                            key={product.id}
                            className={clsx(
                                'flex flex-row items-center justify-between py-4',
                                {
                                    'border-t': i !== 0,
                                },
                            )}
                        >
                            <div className="flex items-center">
                                <ShoppingBagIcon className="h-10 w-10"/>
                                <div className="min-w-0">
                                    <p className="text-sm font-semibold md:text-base">
                                        {product.description}
                                    </p>
                                </div>
                            </div>
                            <div className="flex items-center">
                                <p
                                    className={`${lusitana.className} truncate text-sm font-medium md:text-base`}
                                >
                                    {product.price}
                                </p>
                                <CurrencyEuroIcon className="h-5 w-5"/>
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