KBRANCH ?= "standard/base"

require recipes-kernel/linux/linux-yocto.inc

# board specific branches
KBRANCH_qemuarm  ?= "standard/arm-versatile-926ejs"
KBRANCH_qemuarm64 ?= "standard/qemuarm64"
KBRANCH_qemumips ?= "standard/mti-malta32"
KBRANCH_qemuppc  ?= "standard/qemuppc"
KBRANCH_qemux86  ?= "standard/base"
KBRANCH_qemux86-64 ?= "standard/base"
KBRANCH_qemumips64 ?= "standard/mti-malta64"

SRCREV_machine_qemuarm ?= "0638cb025fd0d8192dbc0acc09018d6fec9f1597"
SRCREV_machine_qemuarm64 ?= "3061011aade2678733665bccd553fdcd60165671"
SRCREV_machine_qemumips ?= "a8971e1548188eb07b88896c7f9d590abb92ed98"
SRCREV_machine_qemuppc ?= "3061011aade2678733665bccd553fdcd60165671"
SRCREV_machine_qemux86 ?= "3061011aade2678733665bccd553fdcd60165671"
SRCREV_machine_qemux86-64 ?= "3061011aade2678733665bccd553fdcd60165671"
SRCREV_machine_qemumips64 ?= "3cff06df7c72b53af99045fcbcdaf2f9285adc1a"
SRCREV_machine ?= "3061011aade2678733665bccd553fdcd60165671"
SRCREV_meta ?= "d15398f64655e36b444d69b3bed43444608abf55"

SRC_URI = "git://git.yoctoproject.org/linux-yocto-4.9.git;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-4.9;destsuffix=${KMETA}"

LINUX_VERSION ?= "4.9.71"

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

KERNEL_DEVICETREE_qemuarm = "versatile-pb.dtb"

COMPATIBLE_MACHINE = "qemuarm|qemuarm64|qemux86|qemuppc|qemumips|qemumips64|qemux86-64"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append_qemuall=" cfg/virtio.scc"
KERNEL_FEATURES_append_qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append_qemux86-64=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"
