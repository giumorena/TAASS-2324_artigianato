import { lusitana } from '@/app/ui/fonts';
import {
  AtSymbolIcon,
  KeyIcon,
  ExclamationCircleIcon,
} from '@heroicons/react/24/outline';
import { ArrowRightIcon } from '@heroicons/react/20/solid';
import { Button } from './button';
import { signIn } from "@/auth"

export default function LoginForm() {
  return (
    <form action={async () => {
      "use server"
      await signIn("keycloak")
    }} className="space-y-3">
      <div className="flex-1 rounded-lg bg-gray-50 px-6 pb-4 pt-8">
        <h1 className={`${lusitana.className} mb-3 text-2xl`}>
          Please log in to continue.
        </h1>
        <LoginButton />
      </div>
    </form>
  );
}

function LoginButton() {
  return (
    <Button type="submit" className="mt-4 w-full">
      Signin with Keycloak <ArrowRightIcon className="ml-auto h-5 w-5 text-gray-50" />
    </Button>
  );
}
