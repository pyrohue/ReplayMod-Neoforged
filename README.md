# Neoforged ReplayMod for 1.21.1

*forked by pyro with love*

This is a fork of [ReforgedPlay](https://github.com/ferriarnus/ReForgedPlay/tree/main) which is a fork of [ReplayMod](https://github.com/ReplayMod/ReplayMod) for NeoForge clients with some additional bug fixes.
It also includes the code of [Replay Voice Chat](https://github.com/henkelmax/replay-voice-chat) since I couldn't get it to work standalone.

>> ⚠️ This is an unofficial port of Replay Mod, do not ask for support on the official Replay Mod Github or Discord

This fork was made with the purpose of playing around with Create Aeronautics on multiplayer servers.

*cough cough blockbound*

Also please keep in mind that I kinda suck at Java at the moment and there are probably a bunch of things wrong in this repo.

# Building
If you want to build this repo for yourself, use the builtin `./gradlew build` (or just `./gradlew bundleJar`). The outputted jar can be in `build/libs`

# Building ReplayStudio (Not Required)
If you want to make changes to ReplayStudio (the nested mod that handles replay files, located in `libs`) then you must compile replay studio
yourself.

Build the repo [ReplayStudio](https://github.com/ReplayMod/ReplayStudio) with JDK 8 or 1.8 on IntelliJ.

I'm not exactly sure how ferriarnus did it for ReForgedPlay but for now you must open the jar as an archive and
remove the specific folders if they exist:

`io`, `us`, `com.google`

Also ensure the name of the jar ends with "-slim", this can be configured in `build.gradle`
Now replace the jar in the `libs` folder.

# Licenses
The ReplayMod is provided under the terms of the GNU General Public License Version 3 or (at your option) any later version.

The Replay Voice Chat is provided under the terms of the MIT License.