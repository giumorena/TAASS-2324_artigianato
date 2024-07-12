import Form from '@/app/ui/craftsmen/edit-form';
import Breadcrumbs from '@/app/ui/breadcrumbs';
import {fetchProductById} from "@/app/lib/data";
import {auth} from "@/auth";
import LoggedInfo from "@/app/ui/logged-info";

export default async function Page({ params }: { params: { id: number, productId: number } }) {
    const id = params.id;
    const productId = params.productId;

    const session = await auth();
    if (!session || !session.user || session.user.category !== 'craftsman') return null;

    const product = await fetchProductById(productId);

    return (
        <main>
            <Breadcrumbs
                breadcrumbs={[
                    { label: 'Craftstore Products', href: `/dashcraftsman/craftstores/${id}/products` },
                    {
                        label: 'Edit product',
                        href: `/dashcraftsman/craftstores/${id}/products/${productId}/edit`,
                        active: true,
                    },
                ]}
            />
            <Form id={id} product={product} />
        </main>
    );
}