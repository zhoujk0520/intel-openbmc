SUMMARY = "Web-based MySQL administration interface"
HOMEPAGE = "http://www.phpmyadmin.net"
# Main code is GPLv2, vendor/tecnickcom/tcpdf is under LGPLv3, js/jquery is under MIT
LICENSE = "GPLv2 & LGPLv3 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://vendor/tecnickcom/tcpdf/LICENSE.TXT;md5=5c87b66a5358ebcc495b03e0afcd342c \
                    file://js/jquery/MIT-LICENSE.txt;md5=e43aa437a6a1ba421653bd5034333bf9 \
"

SRC_URI = "https://files.phpmyadmin.net/phpMyAdmin/${PV}/phpMyAdmin-${PV}-all-languages.tar.xz \
           file://apache.conf \
"

SRC_URI[md5sum] = "0a862e3fa52c8b9078e0441ee82be831"
SRC_URI[sha256sum] = "f19b503f0afb0dd3389d4bb11dab3f438d36e85eef11a5c400c592fd5f539398"

UPSTREAM_CHECK_URI = "https://www.phpmyadmin.net/downloads/"
UPSTREAM_CHECK_REGEX = "phpMyAdmin-(?P<pver>\d+(\.\d+)+)-all-languages.tar.xz"

S = "${WORKDIR}/phpMyAdmin-${PV}-all-languages"

inherit allarch

do_install() {
    install -d ${D}${datadir}/${BPN}
    cp -R --no-dereference --preserve=mode,links -v * ${D}${datadir}/${BPN}
    chown -R root:root ${D}${datadir}/${BPN}
    # Don't install patches to target
    rm -rf ${D}${datadir}/${BPN}/patches

    install -d ${D}${sysconfdir}/apache2/conf.d
    install -m 0644 ${WORKDIR}/apache.conf ${D}${sysconfdir}/apache2/conf.d/phpmyadmin.conf

    # Remove a few scripts that explicitly require bash (!)
    rm -f ${D}${datadir}/phpmyadmin/libraries/transformations/*.sh
}

FILES_${PN} = "${datadir}/${BPN} \
               ${sysconfdir}/apache2/conf.d"

RDEPENDS_${PN} += "bash"
