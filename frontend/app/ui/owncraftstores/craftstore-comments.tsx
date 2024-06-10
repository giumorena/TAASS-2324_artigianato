import {ShowCraftstoreComments, ShowCraftstoreProducts} from "@/app/ui/dashcraftsman/buttons-craftsman";
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
                    <ShowCraftstoreComments id={id} />
                    <ShowCraftstoreProducts id={id} />
                </div>
            </div>
        </div>
    );
}