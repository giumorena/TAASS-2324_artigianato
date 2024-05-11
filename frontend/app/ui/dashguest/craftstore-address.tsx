import {Address} from "@/app/lib/definitions";
import {lusitana} from "@/app/ui/fonts";

export default function CraftstoreAddress({ adlist }: { adlist: Address[] }) {
    return (
        <div className="flex w-full flex-col">
            <h2 className={`${lusitana.className} mb-4 text-xl`}>
                Address
            </h2>
                    <div className="relative text-gray-500 text-sm md:text-base">
                        <ul className="max-w-md space-y-1 list-none">
                            {adlist.map((address) => (
                                <li key={address.id}>
                                    {address.street}, {address.city}
                                    {address.province !== address.city? ' (' +address.province + ')': ''}
                                    {address.cap !== 0? ' cap: ' + address.cap: ''}
                                </li>
                            ))}
                        </ul>
                    </div>

        </div>

    );
}