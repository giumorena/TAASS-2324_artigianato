'use server';

import { z } from 'zod';
import {postComment} from "@/app/lib/data";
import { revalidatePath } from 'next/cache';
import { redirect } from 'next/navigation';

const FormSchema = z.object({
    id: z.number(),
    craftstoreId: z.coerce.number(), // the value from the form is of type string not number
    craftstoreName: z.string().min(1),
    userId: z.number(),
    userName: z.string(),
    postDate: z.string(),
    text: z.string().min(1).max(100),
});

const CreateComment = FormSchema.omit({ id: true, userId: true, userName: true, postDate: true });

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