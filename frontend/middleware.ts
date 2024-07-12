export { auth as middleware } from "@/auth"

// Prevent middleware from running on static files and images (like styles and fonts)
export const config = {
    matcher: ["/((?!api|_next/static|_next/image|favicon.ico).*)"],
}