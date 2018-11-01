package com.plainprog.crystalbookreader;

public class Language {
    public enum Lang {ENG, RUS, UKR, CATALAN,CZECH,DANISH,GERMAN,ESPERANTO,SPANISH,EST,FINNISH,FRENCH,HINDI,
        ARMENIAN,ITALIAN,LATIN,LITHUANIAN,LATVIAN,NO,DUTCH,PL,PT,RO,SLOVAK,SLOVENIAN,SWEDISH,TAMIL, TR, NONE}
    private Lang language;

    public Lang getLanguage() {
        return language;
    }

    public String getjHypenatorLookUp() {
        return jHypenatorLookUp;
    }

    private String jHypenatorLookUp;

    public Language(Lang language) {
        this.language = language;
        jHypenatorLookUp = getLookUpForLang(language);
    }
    public Language(String language){
        switch (language){
            case "ang":
            case "eng":
            case "enm":
            case "en":
                this.language = Lang.ENG;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "ca":
            case "cat":
                this.language = Lang.CATALAN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "cs":
            case "cze":
            case "ces":
                this.language = Lang.CZECH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "da":
            case "dan":
                this.language = Lang.DANISH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "de":
            case "ger":
            case "deu":
            case "gmh":
            case "goh":
                this.language = Lang.GERMAN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "eo":
            case "epo":
                this.language = Lang.ESPERANTO;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "es":
            case "spa":
                this.language = Lang.SPANISH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "et":
            case "est":
                this.language = Lang.EST;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "fi":
            case "fin":
                this.language = Lang.FINNISH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "fr":
            case "fre":
            case "fra":
            case "frm":
            case "fro":
            case "cpf":
                this.language = Lang.FRENCH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "hi":
            case "hin":
                this.language = Lang.HINDI;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "hy":
            case "arm":
            case "hye":
                this.language = Lang.ARMENIAN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "it":
            case "ita":
                this.language = Lang.ITALIAN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "la":
            case "lat":
                this.language = Lang.LATIN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "lt":
            case "lit":
                this.language = Lang.LITHUANIAN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "lv":
            case "lav":
                this.language = Lang.LATVIAN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "nn":
            case "nb":
            case "no":
                this.language = Lang.NO;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "nl":
            case "dut":
            case "nld":
                this.language = Lang.DUTCH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "pl":
            case "pol":
                this.language = Lang.DUTCH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "pt":
            case "por":
            case "cpp":
                this.language = Lang.PT;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "ro":
            case "rum":
            case "rom":
                this.language = Lang.RO;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "ru":
            case "rus":
                this.language = Lang.RUS;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "sk":
            case "slo":
            case "slk":
                this.language = Lang.SLOVAK;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "sl":
            case "slv":
                this.language = Lang.SLOVENIAN;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "sv":
            case "swe":
                this.language = Lang.SWEDISH;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "tr":
            case "tur":
            case "ota":
                this.language = Lang.TR;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "ta":
            case "tam":
                this.language = Lang.TAMIL;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
            case "uk":
            case "ukr":
                this.language = Lang.UKR;
                jHypenatorLookUp = getLookUpForLang(this.language);
                break;
                default:
                    this.language = Lang.NONE;
                    break;
        }
    }
    public String getLookUpForLang(Lang language){
        switch (language){
            case ENG:
                return  "EN_US";
            case CATALAN:
                return  "CA";
            case CZECH:
                return  "CS";
            case DANISH:
                return  "DA";
            case GERMAN:
                return  "DE";
            case ESPERANTO:
                return  "EO";
            case SPANISH:
                return  "ES";
            case EST:
                return  "ET";
            case FINNISH:
                return  "FI";
            case FRENCH:
                return  "FR";
            case HINDI:
                return  "HI";
            case ARMENIAN:
                return  "HY";
            case ITALIAN:
                return  "IT";
            case LATIN:
                return  "LA";
            case LITHUANIAN:
                return  "LT";
            case LATVIAN:
                return  "LV";
            case NO:
                return  "NO";
            case DUTCH:
                return  "NL";
            case PL:
                return  "PL";
            case PT:
                return  "PT";
            case RO:
                return  "RO";
            case RUS:
                return  "RU";
            case SLOVAK:
                return  "SK";
            case SLOVENIAN:
                return  "SL";
            case SWEDISH:
                return  "SV";
            case TR:
                return  "TR";
            case TAMIL:
                return  "TA";
            case UKR:
                return  "UK";
                default: return null;
        }
    }
}
