# 🤝 Contributing to Ultimate Backport Mod (UBM)

Thank you for your interest in contributing to **UBM**! 🎉
This project is community-driven, and we welcome contributions of all kinds: code, bug reports, textures, sounds, and ideas.

To keep contributions smooth and consistent, please read these guidelines before getting started.

## 🛠️ How You Can Contribute
* **Report bugs** – use [GitHub Issues](../../issues) with clear steps to reproduce.
* **Suggest features** – open a Feature Request issue and describe why it should be added.
* **Improve code** – fork the repo and submit a pull request (PR).
* **Create assets** – textures, models, or sounds that fit the project’s style.
* **Documentation** – help improve guides, READMEs, and wikis.

## 📂 Project Setup
1. Install **Minecraft Forge 1.12.2 MDK**
2. Clone this repository:
   ```git
   git clone https://github.com/JWeinelt/UltimateBackportMod.git
   cd UltimateBackportMod
   ```
3. Import into your IDE (IntelliJ IDEA recommended).
4. Run with `gradlew runClient`.


## 💻 Code Guidelines
* Follow **Java conventions** (CamelCase, meaningful names, no unused code).
* Keep methods short and modular.
* Add comments for complex logic.
* One feature/fix per pull request – keep PRs focused.
* Make sure code compiles and passes tests before submitting.

## 🎨 Asset Guidelines
* **Do not copy Minecraft assets.**
  All textures, models, and sounds must be original or adapted in a unique style.
* Keep texture resolution consistent (16x16 unless otherwise specified).
* Submit assets in the correct folder structure (`/src/main/resources/assets/ubm/...`).
* If creating sounds, ensure they are short, clear, and in `.ogg` format.

## 🔄 Workflow
1. Fork the repository
2. Create a feature branch:

```bash
git checkout -b feature/my-new-feature
```

3. Commit changes with clear messages:

```bash
git commit -m "Add backported kelp block"
```

4. Push to your fork and open a Pull Request

## ✅ Pull Request Checklist

Before submitting, make sure:
* [ ] Code compiles without errors
* [ ] No unused imports or debug prints
* [ ] PR title is descriptive (`Fix: Crash when placing kelp` instead of `Update`)
* [ ] Linked to an issue if applicable (`Closes #123`)

## 📜 Code of Conduct
By participating, you agree to respect others and follow our [Code of Conduct](CODE_OF_CONDUCT.md).
Be kind, be constructive, and remember: this is a community-driven project. 💖

✨ *Together, we can make 1.12.2 feel like the future of Minecraft!* ✨
