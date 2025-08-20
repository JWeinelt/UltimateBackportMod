# SECURITY.md

## Supported Versions

UBM follows a simple support policy focused on stability for servers running **Forge 1.12.2**.

| Version branch | Status        | Notes                                 |
| -------------- | ------------- | ------------------------------------- |
| `master`       | ✅ Supported   | Receives security fixes and patches.  |

> Security fixes are released as patch builds. Feature updates may be deferred.


## Reporting a Vulnerability
**Please do not open public Issues or Discussions for security problems.**
Use one of these private channels:
1. **GitHub Security Advisory (preferred):**
   * Go to **Security → Advisories → Report a vulnerability** in this repository.
2. **Email:**
   * Send details to **`security@ubm.julianweinelt.de`**.
   * Subject: `UBM Security Report: <short summary>`
We will acknowledge receipt **within 72 hours** and provide a status update **within 7 days**. Fixes are typically released as soon as practical; coordinated disclosure timelines are below.

## What to Include
* A clear description of the issue and **impact** (e.g., server crash, RCE, data loss).
* **Steps to reproduce**, including minimal config/world if possible.
* Affected **UBM version/build**, Java version, Forge version, and mod list.
* Proof-of-concept (PoC) or crash logs (sanitize secrets).
* Your preferred name/handle for **credit** (optional).

## Scope
Examples of in-scope issues (non-exhaustive):

* Remote crash, denial of service, or world corruption via **network packets**, **NBT**, **entities**, **items**, or **commands**.
* Unauthorized **file I/O** or config manipulation.
* Privilege escalation between **client/server** or **single-/multiplayer** contexts.
* Supply-chain vulnerabilities in our **build, releases, or distribution**.

Out of scope (unless leading to a security impact):

* Cosmetic bugs, typos, or non-security performance issues.
* Crashes caused by **broken third-party mods** without a UBM trigger.
* Minecraft/Forge engine bugs we do not control (we’ll upstream if relevant).

## Coordinated Disclosure

* We prefer **coordinated disclosure**.
* By default, we aim to ship a fix within **30 days** of triage for high-impact issues, and **90 days** otherwise.
* We may request a short extension if a fix requires upstream changes or complex compatibility testing.
* We will credit reporters in release notes unless you request anonymity.

## Severity & Triage
We use a pragmatic severity scale adapted for modded servers:

* **Critical:** Remote code execution; persistent server compromise.
* **High:** Remote world corruption; crash on join; data exfiltration; auth bypass.
* **Medium:** Denial of service requiring admin intervention; privilege confusion.
* **Low:** Info leaks with limited impact; minor sandbox escapes without write access.

Severity informs patch urgency and backport decisions.

## Fix Distribution & Verification

* Fixes are released as **tagged patch versions** on GitHub Releases with detailed notes.
* Server owners should **backup worlds**, then update the `.jar`.
* We may provide **mitigations** (config toggles/feature gates) when a full fix needs more testing.

## Dependency & Supply-Chain Security

* We pin and regularly update build dependencies.
* Releases are built with reproducible settings where feasible; artifacts are attached to GitHub Releases.
* Verify checksums from the release notes before deployment.

## Safe Harbor

We support **good-faith security research**. If you comply with this policy, avoid privacy violations, data destruction, or service degradation, and give us reasonable time to remediate before disclosure, we will not pursue legal action.

---

## Contact

* Security contact: **`security@ubm.julianweinelt.de`**

*Thank you for helping keep players and servers safe. 🛡️*
