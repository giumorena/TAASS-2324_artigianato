import {lusitana} from "@/app/ui/fonts";


export default function CraftstoreGeneral({name,category,description, }:
                                           { name:string,category:string;description:string; }) {
    return (
        <div className="flex w-full flex-col">
            <h2 className={`${lusitana.className} mb-4 text-xl`}>
                General info
            </h2>
                    <dl className="max-w-md divide-y divide-gray-200 text-sm md:text-base">
                        <div className="flex flex-col pb-3">
                            <dt className="mb-1 font-semi-bold">Craftstore Name</dt>
                            <dd className="text-gray-500">{name}</dd>
                        </div>
                        <div className="flex flex-col pb-3">
                            <dt className="mb-1 font-semi-bold">Category</dt>
                            <dd className="text-gray-500">{category}</dd>
                        </div>
                        {description? <div className="flex flex-col pb-3">
                            <dt className="mb-1 font-semi-bold">Description</dt>
                            <dd className="text-gray-500">{description}</dd>
                            </div>:null}
                    </dl>

        </div>
    );
}