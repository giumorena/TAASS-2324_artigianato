import {
  CommentO,
  ProductO,
  UserO,
} from './definitions';
import { formatCurrency } from './utils';

const gatewayURL = process.env.NEXT_APP_API_BASE_URL;

const ok = 'OK';
const notFound = 'Not Found';

// Fetches the sorted page with the search results based on the query string
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchFilteredSortedPagedCraftstores(query: string) {
  const res = await fetch(`${gatewayURL}/craftstore/searchCraftstoresSortedPage?${query}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (res.statusText !== ok && res.statusText !== notFound) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch filtered sorted paged craftstores')
  }

  return res.json()
}

// Fetches the total number of pages with the search results based on the query string
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchNumberOfCraftstoresPages(query: string) {
      const res = await fetch(`${gatewayURL}/craftstore/countCraftstoresPages?${query}`,{cache: 'no-store'})
      // The return value is *not* serialized
      // You can return Date, Map, Set, etc.

      if (!res.ok) {
        // This will activate the closest `error.js` Error Boundary
        throw new Error('Failed to fetch number of craftstores pages')
      }

      return res.json()
}

// Fetches all the information about a craftstore given its id
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchCraftstoreById(id: number) {
  const res = await fetch(`${gatewayURL}/craftstore/getCraftstore/${id}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch craftstore by id')
  }

  return res.json()
}

// Fetches the general information about a craftstore given its id
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchCraftstoreInfoById(id: number) {
  const res = await fetch(`${gatewayURL}/craftstore/getCraftstoreInfo/${id}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch craftstore info by id')
  }

  return res.json()
}

// Fetches the craftstore sampler given the craftstore id
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchCraftstoreProductsById(id: number) {
  const res = await fetch(`${gatewayURL}/craftstore/getCraftstoreSampler/${id}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch craftstore sampler by id')
  }

  return res.json()
}

// Fetches comments related to a craftstore given its id
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchCraftstoreCommentsById(id: number) {
  const res = await fetch(`${gatewayURL}/craftstore/getCraftstoreComments/${id}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (res.statusText !== ok && res.statusText !== notFound) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch craftstore comments by id')
  }

  return res.json()
}

// Fetches comments posted by a user given his id
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchUserCommentsById(id: number) {
  const res = await fetch(`${gatewayURL}/user/getUserComments/${id}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (res.statusText !== ok && res.statusText !== notFound) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch user comments by id')
  }

  return res.json()
}

// Fetches all craftstores sorted by name
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchSortedCraftstores() {
  const res = await fetch(`${gatewayURL}/craftstore/getSortedCraftstores`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch sorted craftstores')
  }

  return res.json()
}

// Fetches all craftstores owned by a craftsman given his id
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchStoresByCraftsmanId(id:number) {
  const res = await fetch(`${gatewayURL}/craftsman/getCraftstores/${id}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (res.statusText !== ok && res.statusText !== notFound) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch craftsman stores')
  }

  return res.json()
}

// Posts a comment
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function postComment(comment: CommentO) {
  const res = await fetch(`${gatewayURL}/comment/addComment`,{
    method: "POST",
    cache : "no-store",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(comment),
  })

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to post comment')
  }

  return res.json()
}

// Adds a product to a sampler
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function addProduct(samplerId: number, product: ProductO) {
  const res = await fetch(`${gatewayURL}/sampler/addProduct/${samplerId}`,{
    method: "POST",
    cache : "no-store",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  })

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to add product')
  }

  return res.json()
}

// Fetches a product given its id
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchProductById(id:number) {
  const res = await fetch(`${gatewayURL}/sampler/getProduct/${id}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch product')
  }

  return res.json()
}

// Update a product
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function updateProduct(productId: number, product: ProductO) {
  const res = await fetch(`${gatewayURL}/sampler/updateProduct/${productId}`,{
    method: "PUT",
    cache : "no-store",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  })

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to update product')
  }

  return res.json()
}

// Delete a product
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function deleteProduct(productId: number) {
  const res = await fetch(`${gatewayURL}/sampler/deleteProduct/${productId}`,{
    method: "DELETE",
    cache : "no-store",
  })

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to delete product')
  }

  return res //the response is empty, so it doesn't have to be parsed into json
}

// Fetches a craftsman given his email
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchCraftsmanByEmail(email:string) {
  const res = await fetch(`${gatewayURL}/craftsman/getCraftsmanByEmail/${email}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch craftsman by email')
  }

  return res.json()
}

// Fetches a user given his email
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function fetchUserByEmail(email:string) {
  const res = await fetch(`${gatewayURL}/user/getUserByEmail/${email}`,{cache: 'no-store'})
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  if (res.statusText === 'OK'){
    return res.json();
  }
  else if(res.statusText === 'Not Found'){
    return {
      status: res.statusText,
    };
  }
  else{
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Error fetching user by email')
  }

}

// Adds to the user database a user who has just registered
// Add noStore() here to prevent the response from being cached.
// This is equivalent to in fetch(..., {cache: 'no-store'}).
export async function addUser(user: UserO) {
  const res = await fetch(`${gatewayURL}/user/addUser`,{
    method: "POST",
    cache : "no-store",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  })

  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to add user')
  }

  return res.json()
}

