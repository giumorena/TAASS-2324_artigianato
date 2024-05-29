import {Contact} from "@/app/lib/definitions";
import {lusitana} from "@/app/ui/fonts";
import {EnvelopeIcon,PhoneIcon} from "@heroicons/react/24/outline";

export default function CraftstoreContact({ conlist }: { conlist: Contact[] }) {
    return (
        <div className="flex w-full flex-col">
            <h2 className={`${lusitana.className} mb-4 text-xl`}>
                Contacts
            </h2>
            <div className="relative text-gray-500 text-sm md:text-base">
                <ul className="max-w-md space-y-1 list-none">
                    {conlist.map((contact) => (
                                    <li key={contact.id}>
                                        {contact.type?(contact.type==='email'?<EnvelopeIcon className="w-5"/>:<PhoneIcon className="w-5"/>):null}
                                        <strong>{contact.contactDetail}</strong>
                                        {contact.description?' ('+contact.description + ')':''}
                                    </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}