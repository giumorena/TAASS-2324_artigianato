import {Craftstore} from "@/app/lib/definitions";
import {
    ChatBubbleLeftEllipsisIcon,
    ShoppingBagIcon,
} from "@heroicons/react/24/outline";
import {Button} from "@/app/ui/button";
import Link from "next/link";
import { createComment } from '@/app/lib/actions';

export default function Form({ craftstores, userId, userName }:
{ craftstores: Craftstore[]; userId: number; userName: string;}) {

    const separator= '-';
    const createCommentWithUserAndSeparator = createComment.bind(null,userId,userName,separator);

    return (
        <form action={createCommentWithUserAndSeparator}>
            <div className="rounded-md bg-gray-50 p-4 md:p-6">

                {/* Craftstore Name */}
                <div className="mb-4">
                    <label htmlFor="craftstore" className="mb-2 block text-sm font-medium">
                        Choose craftstore
                    </label>
                    <div className="relative">
                        <select
                            id="craftstore"
                            name="craftstore"
                            className="peer block w-full cursor-pointer rounded-md border border-gray-200 py-2 pl-10 text-sm outline-2 placeholder:text-gray-500"
                            defaultValue=""
                            required={true}
                        >
                            <option value="" disabled>
                                Select a craftstore
                            </option>
                            {craftstores.map((craftstore) => (
                                <option key={craftstore.id} value={craftstore.id+separator+craftstore.name}>
                                    {craftstore.name}
                                </option>
                            ))}
                        </select>
                        <ShoppingBagIcon className="pointer-events-none absolute left-3 top-1/2 h-[18px] w-[18px] -translate-y-1/2 text-gray-500" />
                    </div>
                </div>

                {/* Comment text */}
                <div className="mb-4">
                    <label htmlFor="comment" className="mb-2 block text-sm font-medium">
                        Enter a comment
                    </label>
                    <div className="relative mt-2 rounded-md">
                        <div className="relative">
                            <input
                                id="comment"
                                name="comment"
                                type="text"
                                placeholder="Enter a comment"
                                className="peer block w-full rounded-md border border-gray-200 py-2 pl-10 text-sm outline-2 placeholder:text-gray-500"
                                required={true}
                            />
                            <ChatBubbleLeftEllipsisIcon className="pointer-events-none absolute left-3 top-1/2 h-[18px] w-[18px] -translate-y-1/2 text-gray-500 peer-focus:text-gray-900" />
                        </div>
                    </div>
                </div>
            </div>

            <div className="mt-6 flex justify-end gap-4">
                <Link
                    href="/dashuser/comments"
                    className="flex h-10 items-center rounded-lg bg-gray-100 px-4 text-sm font-medium text-gray-600 transition-colors hover:bg-gray-200"
                >
                    Cancel
                </Link>
                <Button type="submit">Post Comment</Button>
            </div>
        </form>
    );
}