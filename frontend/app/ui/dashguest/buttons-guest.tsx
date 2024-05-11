import {InformationCircleIcon,ShoppingBagIcon,ChatBubbleLeftRightIcon} from "@heroicons/react/24/outline";
import Link from "next/link";

export function ShowCraftstoreInfo({ id }: { id: number }) {
    return (
        <Link
            href={`/dashguest/search/${id}/info`}
            className="rounded-md border p-2 hover:bg-gray-100"
        >
            <InformationCircleIcon className="w-5" />
        </Link>
    );
}

export function ShowCraftstoreProducts({ id }: { id: number }) {
    return (
        <Link
            href={`/dashguest/search/${id}/products`}
            className="rounded-md border p-2 hover:bg-gray-100"
        >
            <ShoppingBagIcon className="w-5" />
        </Link>
    );
}

export function ShowCraftstoreComments({ id }: { id: number }) {
    return (
        <Link
            href={`/dashguest/search/${id}/comments`}
            className="rounded-md border p-2 hover:bg-gray-100"
        >
            <ChatBubbleLeftRightIcon className="w-5" />
        </Link>
    );
}