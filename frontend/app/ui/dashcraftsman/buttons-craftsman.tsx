import {
    ShoppingBagIcon,
    ChatBubbleLeftRightIcon,
    PencilIcon,
    TrashIcon, PlusIcon
} from "@heroicons/react/24/outline";
import Link from "next/link";
import { cancelProduct } from '@/app/lib/actions';

export function ShowCraftstoreProducts({ id }: { id: number }) {
    return (
        <Link
            href={`/dashcraftsman/craftstores/${id}/products`}
            className="rounded-md border p-2 hover:bg-gray-100"
        >
            <ShoppingBagIcon className="w-5" />
        </Link>
    );
}

export function ShowCraftstoreComments({ id }: { id: number }) {
    return (
        <Link
            href={`/dashcraftsman/craftstores/${id}/comments`}
            className="rounded-md border p-2 hover:bg-gray-100"
        >
            <ChatBubbleLeftRightIcon className="w-5" />
        </Link>
    );
}

export function CreateProduct({ id, samplerId }: { id: number; samplerId: number; }) {
    return (
        <Link
            href={`/dashcraftsman/craftstores/${id}/products/sampler/${samplerId}/create`}
            className="flex h-10 items-center rounded-lg bg-blue-600 px-4 text-sm font-medium text-white transition-colors hover:bg-blue-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600"
        >
            <span className="hidden md:block">New Product</span>{' '}
            <PlusIcon className="h-5 md:ml-4" />
        </Link>
    );
}

export function UpdateProduct({ id, productId }: { id: number; productId:number; }) {
    return (
        <Link
            href={`/dashcraftsman/craftstores/${id}/products/${productId}/edit`}
            className="rounded-md border p-2 hover:bg-gray-100"
        >
            <PencilIcon className="w-5" />
        </Link>
    );
}

export function DeleteProduct({ id, productId }: { id: number; productId:number; }) {
    const cancelProductWithCraftstoreAndId = cancelProduct.bind(null, id, productId);

    return (
        <form action={cancelProductWithCraftstoreAndId}>
            <button className="rounded-md border p-2 hover:bg-gray-100">
                <span className="sr-only">Delete</span>
                <TrashIcon className="w-5" />
            </button>
        </form>
    );
}