import { ShoppingBagIcon } from '@heroicons/react/24/outline';
import { lusitana } from '@/app/ui/fonts';

export default function CraftLogo() {
  return (
    <div
      className={`${lusitana.className} flex flex-row items-center leading-none text-white`}
    >
      <ShoppingBagIcon className="h-10 w-10 rotate-[15deg]" />
      <p className="text-[30px]">Il bello dell&apos;artigianato</p>
    </div>
  );
}
