import Form from '@/app/ui/craftsmen/create-form';
import Breadcrumbs from '@/app/ui/breadcrumbs';

export default async function Page({ params }: { params: { id: number, samplerId: number } }) {
    const id = params.id;
    const samplerId = params.samplerId;

    return (
        <main>
            <Breadcrumbs
                breadcrumbs={[
                    { label: 'Craftstore Products', href: `/dashcraftsman/craftstores/${id}/products` },
                    {
                        label: 'Create product',
                        href: `/dashcraftsman/craftstores/${id}/products/sampler/${samplerId}/create`,
                        active: true,
                    },
                ]}
            />
            <Form id={id} samplerId={samplerId} />
        </main>
    );
}