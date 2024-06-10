import {
    CurrencyEuroIcon,
    ShoppingBagIcon,
} from "@heroicons/react/24/outline";
import {Button} from "@/app/ui/button";
import Link from "next/link";
import {editProduct} from '@/app/lib/actions';
import {Product} from "@/app/lib/definitions";

export default function Form({ id, product }:
                                 { id: number; product: Product;}) {

    const editProductWithCraftstoreAndId = editProduct.bind(null,id,product.id);

    return (
        <form action={editProductWithCraftstoreAndId}>
            <div className="rounded-md bg-gray-50 p-4 md:p-6">

                {/* Product Description */}
                <div className="mb-4">
                    <label htmlFor="product" className="mb-2 block text-sm font-medium">
                        Enter a product
                    </label>
                    <div className="relative mt-2 rounded-md">
                        <div className="relative">
                            <input
                                id="product"
                                name="product"
                                type="text"
                                placeholder="Enter a product"
                                className="peer block w-full rounded-md border border-gray-200 py-2 pl-10 text-sm outline-2 placeholder:text-gray-500"
                                required={true}
                                defaultValue={product.description}
                            />
                            <ShoppingBagIcon className="pointer-events-none absolute left-3 top-1/2 h-[18px] w-[18px] -translate-y-1/2 text-gray-500 peer-focus:text-gray-900" />
                        </div>
                    </div>
                </div>

                {/* Product Price */}
                <div className="mb-4">
                    <label htmlFor="price" className="mb-2 block text-sm font-medium">
                        Enter a price
                    </label>
                    <div className="relative mt-2 rounded-md">
                        <div className="relative">
                            <input
                                id="price"
                                name="price"
                                type="number"
                                step="0.01"
                                placeholder="Enter EURO price"
                                className="peer block w-full rounded-md border border-gray-200 py-2 pl-10 text-sm outline-2 placeholder:text-gray-500"
                                defaultValue={product.price}
                            />
                            <CurrencyEuroIcon className="pointer-events-none absolute left-3 top-1/2 h-[18px] w-[18px] -translate-y-1/2 text-gray-500 peer-focus:text-gray-900" />
                        </div>
                    </div>
                </div>
            </div>

            <div className="mt-6 flex justify-end gap-4">
                <Link
                    href={`/dashcraftsman/craftstores/${id}/products`}
                    className="flex h-10 items-center rounded-lg bg-gray-100 px-4 text-sm font-medium text-gray-600 transition-colors hover:bg-gray-200"
                >
                    Cancel
                </Link>
                <Button type="submit">Update Product</Button>
            </div>
        </form>
    );
}