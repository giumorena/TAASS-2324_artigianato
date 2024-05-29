import {Ownership} from "@/app/lib/definitions";

export default function OwnersList({ owlist }: { owlist: Ownership[] }) {
        return (
            <span>
                <ul className="max-w-md space-y-1 list-none">
                    {owlist.map((ownership) => (
                        <li key={ownership.id}>
                            {ownership.craftsmanName}
                        </li>
                    ))}
                </ul>
            </span>
        );
}


