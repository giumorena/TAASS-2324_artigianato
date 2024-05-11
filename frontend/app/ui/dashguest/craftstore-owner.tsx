import {lusitana} from "@/app/ui/fonts";
import {Ownership} from "@/app/lib/definitions";
import OwnersList from "@/app/ui/dashguest/owners-list";

export default function CraftstoreOwner({ owlist }: { owlist: Ownership[] }) {
    return (
        <div className="flex w-full flex-col">
            <h2 className={`${lusitana.className} mb-4 text-xl`}>
                Owners
            </h2>
                    <div className="relative text-gray-500 text-sm md:text-base">
                        <OwnersList owlist={owlist}/>
                    </div>
        </div>
    );
}