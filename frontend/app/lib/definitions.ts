// This file contains type definitions for your data.
// It describes the shape of the data, and what data type each property should accept.
// For simplicity of teaching, we're manually defining these types.
// However, these types are generated automatically if you're using an ORM such as Prisma.

export type OwnershipS = {
  id: number;
  craftstoreId: number;
  craftstoreName: string;
}

export type Ownership = {
  id: number;
  craftsmanId: number;
  craftsmanName: string;
}

export type Craftstore = {
  id: number;
  name: string;
  category: string;
  description: string;
  ownerList: Ownership[];
}

export type Address = {
  id: number;
  region: string;
  province: string;
  city: string;
  street: string;
  cap: number;
}

export type Contact = {
  id: number;
  type: string;
  contactDetail: string;
  description: string;
}

export type Product = {
  id: number;
  description: string;
  price: number;
}

export type CraftstoreComment = {
  id: number;
  userId: number;
  userName: string;
  postDate: string;
  text: string;
}

export type UserComment = {
  id: number;
  craftstoreId: number;
  craftstoreName: string;
  postDate: string;
  text: string;
}

// Comment with omitted fields
export type CommentO = {
  //id: number;
  craftstoreId: number;
  craftstoreName: string;
  userId: number;
  userName: string;
  //postDate: string;
  text: string;
}

// Product with omitted fields
export type ProductO = {
  //id: number;
  description: string;
  price: number;
}

// User with omitted fields
export type UserO = {
  //id: number;
  name: string;
  email: string;
  commentList: UserComment[];
};
