import sysmicro.grails.BioEntity
import sysmicro.grails.Term
import sysmicro.grails.EntityAnnotation
import com.grailsrocks.authentication.AuthenticationUser
import com.grailsrocks.authentication.AuthenticationService
import org.springframework.web.context.support.WebApplicationContextUtils
import sysmicro.grails.User
import uk.ac.ebi.sysmicro.owl.OWLCPOOntology

class   BootStrap {

    def ontologyService

    def init = { servletContext ->

//        def appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
//        appCtx.authenticationService.events.onSaveUser = { user ->
//
//            if (user.save()) {
//                if (!User.getUserByLogin(user.login))    {
//                    println('creating new user: '  + user.login + " / " +  user.email)
//                    new User(login: user.login, password: user.password, email: user.email ).save()
//                }
//            }
//        }

//        new AuthenticationUser( login:'jupp', password:'hellojupp'.encodeAsMD5(), email:'jupp@ebi.ac.uk',
//            status:AuthenticationService.STATUS_VALID).save()

//        Term mitochondria = new Term(uri: 'http://example.com/cell/mitochondria', label: 'mitochondria').save()
//        Term largeQuality = new Term(uri: 'http://example.com/quality/large', label: 'large').save()
//        Term smallQuality = new Term(uri: 'http://example.com/quality/small', label: 'small').save()
//

//        for (int z = 0; z< 20; z++) {
//            String acc = 'aaa' + z;
//            BioEntity be = new BioEntity(phenotype: 'this is a phenotype ' + acc)
////            EntityAnnotation annotation = new EntityAnnotation(entity:mitochondria, quality:largeQuality, value:'', accuracy:2)
////            EntityAnnotation annotation1 = new EntityAnnotation(entity:mitochondria, quality:smallQuality, value:'', accuracy:1)
////            be.addToEntityAnnotation(annotation)
////            be.addToEntityAnnotation(annotation1)
//
//            if (!be.save()){
//              log.error "Could not save entity!!"
//              log.error "${be.errors}"
//            }
//        }

//        new BioEntity(phenotype: "Abundance of large cells with protrusions and bright nuclei", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Acentrosomal spindle poles", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Actin fiber cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Altered focal adhesion (FA) or cell shape morphology", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Apoptosis resistance", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Average or increased nuclei size in G1", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Big cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Bright nuclei", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Cell cycle / mitosis defect", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Cell division defect", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Cells with protrusions", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Cytoplasmic 40S maturation defects", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased 12E8 and total tau protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased 12E8 tau but not total tau protein expression and decreased ratio of 12E8/total tau protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased and increased HIV-LTR-beta-galactosidase protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased BPV1 E2 protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased cell elongation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased cell number", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased cell number and increased metaphase cell number", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased cell spreading", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased cilium length", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased cilium length after serum starvation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased circadian period length", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased CXCL12 induced migration", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased CYP1A1 activity after TCDD stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased DCP1a protein expression and assembly in processing bodies after arsenite stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased FA abundance", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased FA length", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased FA mean intensity", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased focal adhesion (FA) abundance", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased focal adhesion (FA) area", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased G3BP1 protein expression and assembly in stress granules after arsenite stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased G3BP1 protein expression and assembly in stress granules and decreased DCP1a protein expression and assembly in processing bodies after arsenite stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Hepatitis C virus replication", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased HIV-LTR-beta-galactosidase protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased homologous recombination repair frequency", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased IFN-gamma protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased IL-10 protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased IL-13 protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased influenza A H1N1 (A/Hamburg/04/2009) virus numbers", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased influenza A H1N1 (A/WSN/33) virus numbers", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased influenza A virus infection", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased influenza A/WSN/33 replication", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased melanin production", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased mitotic index", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased NANOG protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased nuclei size in G2M", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased number of cells in monopolar arrest with EMD534085 (a Kinesin-5 inhibitor)", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased number of small and round FAs", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased OCT4 protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased p24 protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased peripheral FA formation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased POU5F1-GFP protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Salmonella enterica Typhimurium binding", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Salmonella enterica Typhimurium effector injection", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Salmonella enterica Typhimurium invasion", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Salmonella enterica Typhimurium membrane closure", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Salmonella enterica Typhimurium ruffling", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Salmonella-containing vacuole maturation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased sensitivity to paclitaxel", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased substrate adherent cell growth", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Tat-dependent HIV-LTR-beta-galactosidase protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Tat-dependent transcription", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased telomerase activity", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased TP53 protein expression ratio (wild-type / TP53 knockout cells)", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability after Chlamydia trachomatis serovar L2 infection and TNF-alpha/CHX stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability in CMK cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability in HMC1.1 cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability of wild-type and TP53 knockout cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability with carboplatin", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability with cisplatin", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability with paclitaxel", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability with RAF265", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased viability with TRAIL", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Decreased Wnt reporter activity", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Downregulation of NF-kappaB pathway after IL-1beta stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Downregulation of NF-kappaB pathway after LMP1 stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Downregulation of NF-kappaB pathway after TNFalpha stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Downregulation of TNF-ë±/NF-ë_B pathway", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Elongated cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Elongated cells with protrusions", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Enable proliferation in B-Raf background", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Essential for multiple myeloma proliferation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "G0/1 arrest", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "G2 arrest", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Gemcitabine induced cell-death increased", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "High actin ratio cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased 8N DNA content", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Akt phosphorylation after EGF stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased apoptosis", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell death HMECs cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell death in breast cancer cell lines (MCF10A,   MDA-MB-435)", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell death in DLD-1 cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell death in HCC-1954 cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell death in HCT116 cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell elongation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell number in G1", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell number in G2M", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell number in S", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell number in S and G2M", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell size", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell spreading", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cell transformation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cilium length", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased cilium length after serum starvation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased circadian period length", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased circadian rhythm amplitude", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased colony dispersion (increased number of colonies and decreased number of cells per colony)", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased CXCL12 induced migration", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Erk phosphorylation after EGF stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased FA abundance", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased focal adhesion (FA) abundance", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased focal adhesion (FA) area", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased G1 DNA content", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased G2M DNA content", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased gamma-H2AX phosphorylation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased HDAC inhibitor resistance", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Hepatitis C virus replication", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased HIV-LTR-beta-galactosidase protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased homologous recombination repair frequency", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased HPV18 LCR reporter activity", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased ID2::GFP protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased IFN-gamma protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased IL-10 protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased IL-13 protein expression", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased influenza A virus infection", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased mitotic exit time", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased mitotic index", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased NF-kappaB reporter activity", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased nuclei size in G2M", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased number of cells in monopolar arrest with EMD534085 (a Kinesin-5 inhibitor)", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased number of cells with high perimeter-to-area ratio nuclei", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased number of mitotic cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased number of polyploid cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased number of small and round FAs", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased number of small and round focal adhesions (FAs)", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased peripheral FA formation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased peripheral focal adhesion (FA) formation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased release from monastrol-induced mitotic arrest", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased resistance to MDM2 inhibitor", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased resistance to Trastuzumab", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased S DNA content", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Salmonella enterica Typhimurium binding", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Salmonella enterica Typhimurium effector injection", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Salmonella enterica Typhimurium invasion", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Salmonella enterica Typhimurium membrane closure", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Salmonella enterica Typhimurium ruffling", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased Salmonella-containing vacuole maturation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased sensitivity to paclitaxel", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased telomerase activity", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased TP53 protein expression ratio (wild-type / TP53 knockout cells)", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased viability after borna disease (rVSVëÓG*/BDVG) virus infection", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased viability with 4OH tamoxifen", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased viability with paclitaxel", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increased viability with TRAIL", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Increases Tamoxifen sensitivity", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Inhibition of centrosomal clustering", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Lamellipodia and high actin ratio cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Lamellipodia cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Large cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Large nuclei", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Low eccentricity cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Metaphase cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Multipolar spindles", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Nuclear 40S maturation defects", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Nuclear 60S biogenesis defects", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Nuclear pre-40S maturation defects", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Nucleolar pre-40S maturation defects", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Nucleoplasmic pre-40S maturation defects", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Paclitaxel antagonistic effect", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Paclitaxel sensitizing effect", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "PARP inhibitor sensitization", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Prevent p53-dependent growth arret", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Proliferating cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Reduced beta-catenin activity and colon cancer cell proliferation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "S arrest", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Small cells", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Small nuclei in G1", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Stronger migration", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Synthetic lethal with c-Myc after tamoxifen stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Synthetic lethal with cisplatin", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Synthetic lethal with gemcitabine", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Synthetic lethal with imatinib mesylate", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Synthetic lethal with paclitaxel", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Synthetic lethal with Ras", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Synthetic lethal/sick with VHL", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Upregulation of NF-kappaB pathway after LMP1 stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Upregulation of Wnt/beta-catenin pathway after WNT3A stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Upregulation of Wnt/beta-catenin pathway after WNT3A stimulation after WNT3A stimulation", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Weaker migration", comment: "human genome phenotype RNAi" ).save();
//        new BioEntity(phenotype: "Wnt reporter downregulated", comment: "human genome phenotype RNAi" ).save();

        ontologyService.initialise (new OWLCPOOntology(), BioEntity.list());

    }
    def destroy = {

    }
}
