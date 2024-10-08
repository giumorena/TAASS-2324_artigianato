import {ShowCraftstoreComments, ShowCraftstoreInfo, ShowCraftstoreProducts} from "@/app/ui/dashguest/buttons-guest";
import Comments from "@/app/ui/comments";

export default async function CraftstoreComments({
                                                     id,
                                                 }: {
    id: number;
}) {

    return (
        <div className="mt-6 flow-root">
            <div className="inline-block min-w-full align-middle">
                <Comments id={id}/>
                <div className="flex justify-center">
                    <ShowCraftstoreInfo id={id} />
                    <ShowCraftstoreProducts id={id} />
                    <ShowCraftstoreComments id={id} />
                </div>
            </div>
        </div>
    );
}