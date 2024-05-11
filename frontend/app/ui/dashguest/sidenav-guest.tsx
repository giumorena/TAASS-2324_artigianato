import Link from 'next/link';
import NavLinksGuest from '@/app/ui/dashguest/nav-links-guest';
import CraftLogo from '@/app/ui/craft-logo';

export default function SideNavGuest() {
    return (
        <div className="flex flex-col px-3 py-4 md:px-2">

            <div className="mb-4 flex h-20 items-end justify-start rounded-md bg-blue-600 p-2 md:h-40">
                    <CraftLogo />
            </div>
            <div className="flex grow flex-row justify-between space-x-2 md:flex-col md:space-x-0 md:space-y-2">
                <NavLinksGuest />
            </div>
        </div>
    );
}