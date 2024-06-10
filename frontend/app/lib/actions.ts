'use server';

import { z } from 'zod';
import {addProduct, updateProduct, deleteProduct, postComment} from "@/app/lib/data";
import { revalidatePath } from 'next/cache';
import { redirect } from 'next/navigation';

const FormSchemaComment = z.object({
    id: z.number(),
    craftstoreId: z.coerce.number(), // the value from the form is of type string not number
    craftstoreName: z.string().min(1),
    userId: z.number(),
    userName: z.string(),
    postDate: z.string(),
    text: z.string().min(1).max(100),
});

const CreateComment = FormSchemaComment.omit({ id: true, userId: true, userName: true, postDate: true });

export async function createComment(userId: number, userName: string, separator: string, formData: FormData) {

    const craftstore=formData.get('craftstore');

    // this check is to avoid the typescript error: the object is possibly null
    if(craftstore) {
        const craftstoreId = craftstore.toString().split(separator)[0];
        const craftstoreName = craftstore.toString().split(separator)[1];

        const validatedData = CreateComment.safeParse({
            craftstoreId: craftstoreId,
            craftstoreName: craftstoreName,
            text: formData.get('comment'),
        });

        if (validatedData.success) {
            const comment = {
                craftstoreId: validatedData.data.craftstoreId,
                craftstoreName: validatedData.data.craftstoreName,
                userId: userId,
                userName: userName,
                text: validatedData.data.text,
            }

            console.log(comment);

            try {
                await postComment(comment);
            } catch (error) {
                  console.log('Post comment: failed to Post Comment');
            }

            revalidatePath('/dashuser/comments');
            redirect('/dashuser/comments');

        } else {
            console.log(validatedData.error.flatten().fieldErrors);
        }
    } else {
        console.log('Post Comment: crafstore is null');
    }

}

const FormSchemaProduct = z.object({
    id: z.number(),
    description: z.string().min(1),
    price: z.coerce.number().nonnegative(), // the value from the form is of type string not number, if the field is empty the value will be set to 0 (not null because the primitive type float was used in the model)
    /*price: z.preprocess(
        (arg) => (arg === '' ? null : arg), //convert empty field to null
        z.coerce.number().positive().nullable(),
    ),*/
});

const CreateProduct = FormSchemaProduct.omit({ id: true });

export async function createProduct(id: number, samplerId: number, formData: FormData) {

    const validatedData = CreateProduct.safeParse({
        description: formData.get('product'),
        price: formData.get('price'),
    });

    if (validatedData.success) {
        const product = {
            description: validatedData.data.description,
            price: validatedData.data.price,
        }

        console.log(product);

        try {
            await addProduct(samplerId,product);
        } catch (error) {
            console.log('Add product: failed to Add Product');
        }

        revalidatePath(`/dashcraftsman/craftstores/${id}/products`);
        redirect(`/dashcraftsman/craftstores/${id}/products`);

    } else {
        console.log(validatedData.error.flatten().fieldErrors);
    }

}

const UpdateProduct = FormSchemaProduct.omit({ id: true });

export async function editProduct(id: number, productId: number, formData: FormData) {

    const validatedData = UpdateProduct.safeParse({
        description: formData.get('product'),
        price: formData.get('price'),
    });

    if (validatedData.success) {
        const product = {
            description: validatedData.data.description,
            price: validatedData.data.price,
        }

        console.log(product);

        try {
            await updateProduct(productId,product);
        } catch (error) {
            console.log('Update product: failed to Update Product');
        }

        revalidatePath(`/dashcraftsman/craftstores/${id}/products`);
        redirect(`/dashcraftsman/craftstores/${id}/products`);

    } else {
        console.log(validatedData.error.flatten().fieldErrors);
    }

}

export async function cancelProduct(id: number, productId: number) {
    console.log(productId);

    try {
        await deleteProduct(productId);
    } catch (error) {
        console.log('Delete product: failed to Delete Product');
    }

    revalidatePath(`/dashcraftsman/craftstores/${id}/products`);
}