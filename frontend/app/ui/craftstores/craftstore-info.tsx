import {fetchCraftstoreInfoById} from "@/app/lib/data";
import CraftstoreGeneral from "@/app/ui/craftstores/craftstore-general";
import CraftstoreAddress from "@/app/ui/craftstores/craftstore-address";
import CraftstoreOwner from "@/app/ui/craftstores/craftstore-owner";
import CraftstoreContact from "@/app/ui/craftstores/craftstore-contact";
import {ShowCraftstoreComments, ShowCraftstoreInfo, ShowCraftstoreProducts} from "@/app/ui/dashguest/buttons-guest";

export default async function CraftstoreInfo({
                                                    id,
                                                }: {
    id: number;
}) {
    const craftstoreInfo = await fetchCraftstoreInfoById(id);

    return (
        <div className="flex grow flex-col justify-between rounded-xl bg-gray-50 p-4">
            <div className="bg-white px-6">
                <div className="grid gap-6 grid-cols-2">
                    <div>
                        <CraftstoreGeneral name={craftstoreInfo.name} category={craftstoreInfo.category} description={craftstoreInfo.description}/>
                    </div>
                    <div>
                        <CraftstoreOwner owlist={craftstoreInfo.ownerList}/>
                    </div>
                    <div>
                        <CraftstoreContact conlist={craftstoreInfo.contactList}/>
                    </div>
                    <div>
                        <CraftstoreAddress adlist={craftstoreInfo.addressList}/>
                    </div>
                    <div className="col-span-2 flex justify-center gap-3">
                        <ShowCraftstoreInfo id={id} />
                        <ShowCraftstoreProducts id={id} />
                        <ShowCraftstoreComments id={id} />
                    </div>
                </div>
            </div>
        </div>
    );
}