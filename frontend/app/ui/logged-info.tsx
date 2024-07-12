import { UserCircleIcon } from '@heroicons/react/24/outline';
import { lusitana } from '@/app/ui/fonts';
import {auth} from "@/auth";

export default async function LoggedInfo() {

    const session = await auth();
    if(!session || !session.user || !session.user.name) return null;

    const name = session.user.name; //username from login

    return (
        <div className={`${lusitana.className} flex flex-row items-center leading-none text-white`}>
            <UserCircleIcon className="h-7 w-7" />
            <p className="font-bold text-base">{name}</p>
        </div>
    );
}