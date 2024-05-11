'use client';

import { MagnifyingGlassIcon } from '@heroicons/react/24/outline';
import { useSearchParams, usePathname, useRouter } from 'next/navigation';

export default function CraftstoreSearch() {
    const searchParams = useSearchParams();
    const pathname = usePathname();
    const { replace } = useRouter();
    const params = new URLSearchParams(searchParams);

    function handleFieldChange(field: string,term: string) {

        params.set('page', '1');
        if (term) {
            params.set(field, term);
        } else {
            params.delete(field);
        }
    }

    function handleSearch(){
        replace(`${pathname}?${params.toString()}`);
    }

  return (
    <form>
        <div className="rounded-md bg-gray-50 p-2 md:p-6">
            <div className="flex flex-wrap -mx-3 mb-6">
                <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                    <MagnifyingGlassIcon className="absolute h-[18px] w-[18px] translate-x-1/2 translate-y-3 text-gray-500 peer-focus:text-gray-900" />
                  <label htmlFor="name" className="sr-only">
                    Craftstore Name
                  </label>
                  <input
                    id="name"
                    className="peer block w-full rounded-md border border-gray-200 py-[9px] pl-10 text-sm outline-2 placeholder:text-gray-500"
                    placeholder="Craftstore Name"

                    onChange={(e) => {
                        handleFieldChange('name',e.target.value);
                    }}
                    defaultValue={searchParams.get('name')?.toString()}
                  />
                </div>
                <div className="w-full md:w-1/2 px-3">
                    <MagnifyingGlassIcon className="absolute h-[18px] w-[18px] translate-x-1/2 translate-y-3 text-gray-500 peer-focus:text-gray-900" />
                  <label htmlFor="category" className="sr-only">
                      Category
                  </label>
                  <input
                    id="category"
                    className="peer block w-full rounded-md border border-gray-200 py-[9px] pl-10 text-sm outline-2 placeholder:text-gray-500"
                    placeholder="Category"

                    onChange={(e) => {
                        handleFieldChange('category',e.target.value);
                    }}
                    defaultValue={searchParams.get('category')?.toString()}
                  />
                </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
                <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                    <MagnifyingGlassIcon className="absolute h-[18px] w-[18px] translate-x-1/2 translate-y-3 text-gray-500 peer-focus:text-gray-900" />
                  <label htmlFor="region" className="sr-only">
                      Region
                  </label>
                  <input
                    id="region"
                    className="peer block w-full rounded-md border border-gray-200 py-[9px] pl-10 text-sm outline-2 placeholder:text-gray-500"
                    placeholder="Region"

                    onChange={(e) => {
                        handleFieldChange('region',e.target.value);
                    }}
                    defaultValue={searchParams.get('region')?.toString()}
                  />
                </div>
                <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                    <MagnifyingGlassIcon className="absolute h-[18px] w-[18px] translate-x-1/2 translate-y-3 text-gray-500 peer-focus:text-gray-900" />
                  <label htmlFor="province" className="sr-only">
                      Province
                  </label>
                  <input
                    id="province"
                    className="peer block w-full rounded-md border border-gray-200 py-[9px] pl-10 text-sm outline-2 placeholder:text-gray-500"
                    placeholder="Province"

                    onChange={(e) => {
                        handleFieldChange('province',e.target.value);
                    }}
                    defaultValue={searchParams.get('province')?.toString()}
                  />
                </div>
                <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                    <MagnifyingGlassIcon className="absolute h-[18px] w-[18px] translate-x-1/2 translate-y-3 text-gray-500 peer-focus:text-gray-900" />
                  <label htmlFor="city" className="sr-only">
                      City
                  </label>
                  <input
                    id="city"
                    className="peer block w-full rounded-md border border-gray-200 py-[9px] pl-10 text-sm outline-2 placeholder:text-gray-500"
                    placeholder="City"

                    onChange={(e) => {
                        handleFieldChange('city',e.target.value);
                    }}
                    defaultValue={searchParams.get('city')?.toString()}
                  />
                </div>
            </div>
            <div className="md:flex md:items-center">
                <div className="md:w-1/3"></div>
                <div className="md:w-1/3">
                    <button
                        className="bg-green-500 hover:bg-green-400 text-white font-bold py-2 px-4 rounded"
                        onClick={() => {handleSearch()}}
                    >
                        Search
                    </button>
                </div>
                <div className="md:w-1/3"></div>
            </div>
        </div>
    </form>
  );
}
