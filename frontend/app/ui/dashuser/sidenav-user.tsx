import Link from 'next/link';
import NavLinksUser from '@/app/ui/dashuser/nav-links-user';
import CraftLogo from '@/app/ui/craft-logo';
import {PowerIcon} from "@heroicons/react/24/outline";
import { signOut } from '@/auth';
import LoggedInfo from "@/app/ui/logged-info";

export default function SideNavUser() {
    return (
        <div className="flex h-full flex-col px-3 py-4 md:px-2">

            <div className="mb-4 flex h-20 items-end justify-start rounded-md bg-blue-600 p-2 md:h-40">
                <CraftLogo />
            </div>
            <div className="mb-4 flex h-10 items-end justify-start rounded-md bg-green-500 p-2 md:h-15">
                <LoggedInfo />
            </div>
            <div className="flex grow flex-row justify-between space-x-2 md:flex-col md:space-x-0 md:space-y-2">
                <NavLinksUser />
                <div className="hidden h-auto w-full grow rounded-md bg-gray-50 md:block"></div>
                <form
                    action={async () => {
                        'use server';
                        await signOut({ redirectTo: "/" });
                    }}
                >
                    <button className="flex h-[48px] w-full grow items-center justify-center gap-2 rounded-md bg-gray-50 p-3 text-sm font-medium hover:bg-sky-100 hover:text-blue-600 md:flex-none md:justify-start md:p-2 md:px-3">
                        <PowerIcon className="w-6" />
                        <div className="hidden md:block">Sign Out</div>
                    </button>
                </form>
            </div>
        </div>
    );
}