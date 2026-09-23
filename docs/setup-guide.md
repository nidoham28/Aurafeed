# Developer Setup Guide

## Prerequisites

- **JDK 17 or JDK 21**
- **Android Studio** (2024.1+ recommended) with Kotlin Multiplatform plugin
- **Xcode** (for iOS build on macOS)

## Supabase Local Setup

1. Install Supabase CLI:
   ```bash
   brew install supabase/tap/supabase
   ```
2. Start local Supabase instance:
   ```bash
   supabase start
   ```
3. Update `SupabaseClientProvider` with your local URL and anon key.
