package com.example.sujin.opencomplaintss;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostProblem extends Fragment {
    String temp="";
    EditText title, description;
    TextView date;
    Spinner location, depart;
    MultiAutoCompleteTextView multi;
    SharedPreferences sharedPreferences;
    String timeStamp, username;
    String[] badWords = {"a55",
            "a55hole",
            "	aeolus	",
            "	ahole	",
            "	anal	",
            "	analprobe	",
            "	anilingus	",
            "	anus	",
            "	areola	",
            "	areole	",
            "	arian	",
            "	aryan	",
            "	ass	",
            "	assbang	",
            "	assbanged	",
            "	assbangs	",
            "	asses	",
            "	assfuck	",
            "	assfucker	",
            "	assh0le	",
            "	asshat	",
            "	assho1e	",
            "	ass hole	",
            "	assholes	",
            "	assmaster	",
            "	assmunch	",
            "	asswipe	",
            "	asswipes	",
            "	azazel	",
            "	azz	",
            "	b1tch	",
            "	babe	",
            "	babes	",
            "	ballsack	",
            "	bang	",
            "	banger	",
            "	barf	",
            "	bastard	",
            "	bastards	",
            "	bawdy	",
            "	beaner	",
            "	beardedclam	",
            "	beastiality	",
            "	beatch	",
            "	beater	",
            "	beaver	",
            "	beer	",
            "	beeyotch	",
            "	beotch	",
            "	biatch	",
            "	bigtits	",
            "	big tits	",
            "	bimbo	",
            "	bitch	",
            "	bitched	",
            "	bitches	",
            "	bitchy	",
            "	blow job	",
            "	blow	",
            "	blowjob	",
            "	blowjobs	",
            "	bod	",
            "	bodily	",
            "	boink	",
            "	bollock	",
            "	bollocks	",
            "	bollok	",
            "	bone	",
            "	boned	",
            "	boner	",
            "	boners	",
            "	bong	",
            "	boob	",
            "	boobies	",
            "	boobs	",
            "	booby	",
            "	booger	",
            "	bookie	",
            "	bootee	",
            "	bootie	",
            "	booty	",
            "	booze	",
            "	boozer	",
            "	boozy	",
            "	bosom	",
            "	bosomy	",
            "	bowel	",
            "	bowels	",
            "	bra	",
            "	brassiere	",
            "	breast	",
            "	breasts	",
            "	bugger	",
            "	bukkake	",
            "	bullshit	",
            "	bull shit	",
            "	bullshits	",
            "	bullshitted	",
            "	bullturds	",
            "	bung	",
            "	busty	",
            "	butt	",
            "	butt fuck	",
            "	buttfuck	",
            "	buttfucker	",
            "	buttfucker	",
            "	buttplug	",
            "	c.0.c.k	",
            "	c.o.c.k.	",
            "	c.u.n.t	",
            "	c0ck	",
            "	c-0-c-k	",
            "	caca	",
            "	cahone	",
            "	cameltoe	",
            "	carpetmuncher	",
            "	cawk	",
            "	cervix	",
            "	chinc	",
            "	chincs	",
            "	chink	",
            "	chink	",
            "	chode	",
            "	chodes	",
            "	cl1t	",
            "	climax	",
            "	clit	",
            "	clitoris	",
            "	clitorus	",
            "	clits	",
            "	clitty	",
            "	cocain	",
            "	cocaine	",
            "	cock	",
            "	c-o-c-k	",
            "	cockblock	",
            "	cockholster	",
            "	cockknocker	",
            "	cocks	",
            "	cocksmoker	",
            "	cocksucker	",
            "	cock sucker	",
            "	coital	",
            "	commie	",
            "	condom	",
            "	coon	",
            "	coons	",
            "	corksucker	",
            "	crabs	",
            "	crack	",
            "	cracker	",
            "	crackwhore	",
            "	crap	",
            "	crappy	",
            "	cum	",
            "	cummin	",
            "	cumming	",
            "	cumshot	",
            "	cumshots	",
            "	cumslut	",
            "	cumstain	",
            "	cunilingus	",
            "	cunnilingus	",
            "	cunny	",
            "	cunt	",
            "	cunt	",
            "	c-u-n-t	",
            "	cuntface	",
            "	cunthunter	",
            "	cuntlick	",
            "	cuntlicker	",
            "	cunts	",
            "	d0ng	",
            "	d0uch3	",
            "	d0uche	",
            "	d1ck	",
            "	d1ld0	",
            "	d1ldo	",
            "	dago	",
            "	dagos	",
            "	dammit	",
            "	damn	",
            "	damned	",
            "	damnit	",
            "	dawgie-style	",
            "	dick	",
            "	dickbag	",
            "	dickdipper	",
            "	dickface	",
            "	dickflipper	",
            "	dickhead	",
            "	dickheads	",
            "	dickish	",
            "	dick-ish	",
            "	dickripper	",
            "	dicksipper	",
            "	dickweed	",
            "	dickwhipper	",
            "	dickzipper	",
            "	diddle	",
            "	dike	",
            "	dildo	",
            "	dildos	",
            "	diligaf	",
            "	dillweed	",
            "	dimwit	",
            "	dingle	",
            "	dipship	",
            "	doggie-style	",
            "	doggy-style	",
            "	dong	",
            "	doofus	",
            "	doosh	",
            "	dopey	",
            "	douch3	",
            "	douche	",
            "	douchebag	",
            "	douchebags	",
            "	douchey	",
            "	drunk	",
            "	dumass	",
            "	dumbass	",
            "	dumbasses	",
            "	dummy	",
            "	dyke	",
            "	dykes	",
            "	ejaculate	",
            "	enlargement	",
            "	erect	",
            "	erection	",
            "	erotic	",
            "	essohbee	",
            "	extacy	",
            "	extasy	",
            "	f.u.c.k	",
            "	fack	",
            "	fag	",
            "	fagg	",
            "	fagged	",
            "	faggit	",
            "	faggot	",
            "	fagot	",
            "	fags	",
            "	faig	",
            "	faigt	",
            "	fannybandit	",
            "	fart	",
            "	fartknocker	",
            "	fat	",
            "	felch	",
            "	felcher	",
            "	felching	",
            "	fellate	",
            "	fellatio	",
            "	feltch	",
            "	feltcher	",
            "	fisted	",
            "	fisting	",
            "	fisty	",
            "	floozy	",
            "	foad	",
            "	fondle	",
            "	foobar	",
            "	foreskin	",
            "	freex	",
            "	frigg	",
            "	frigga	",
            "	fubar	",
            "	fuck	",
            "	f-u-c-k	",
            "	fuckass	",
            "	fucked	",
            "	fucked	",
            "	fucker	",
            "	fuckface	",
            "	fuckin	",
            "	fucking	",
            "	fucknugget	",
            "	fucknut	",
            "	fuckoff	",
            "	fucks	",
            "	fucktard	",
            "	fuck-tard	",
            "	fuckup	",
            "	fuckwad	",
            "	fuckwit	",
            "	fudgepacker	",
            "	fuk	",
            "	fvck	",
            "	fxck	",
            "	gae	",
            "	gai	",
            "	ganja	",
            "	gay	",
            "	gays	",
            "	gey	",
            "	gfy	",
            "	ghay	",
            "	ghey	",
            "	gigolo	",
            "	glans	",
            "	goatse	",
            "	godamn	",
            "	godamnit	",
            "	goddam	",
            "	goddammit	",
            "	goddamn	",
            "	goldenshower	",
            "	gonad	",
            "	gonads	",
            "	gook	",
            "	gooks	",
            "	gringo	",
            "	gspot	",
            "	g-spot	",
            "	gtfo	",
            "	guido	",
            "	h0m0	",
            "	h0mo	",
            "	handjob	",
            "	hard on	",
            "	he11	",
            "	hebe	",
            "	heeb	",
            "	hell	",
            "	hemp	",
            "	heroin	",
            "	herp	",
            "	herpes	",
            "	herpy	",
            "	hitler	",
            "	hiv	",
            "	hobag	",
            "	hom0	",
            "	homey	",
            "	homo	",
            "	homoey	",
            "	honky	",
            "	hooch	",
            "	hookah	",
            "	hooker	",
            "	hoor	",
            "	hootch	",
            "	hooter	",
            "	hooters	",
            "	horny	",
            "	hump	",
            "	humped	",
            "	humping	",
            "	hussy	",
            "	hymen	",
            "	inbred	",
            "	incest	",
            "	injun	",
            "	j3rk0ff	",
            "	jackass	",
            "	jackhole	",
            "	jackoff	",
            "	jap	",
            "	japs	",
            "	jerk	",
            "	jerk0ff	",
            "	jerked	",
            "	jerkoff	",
            "	jism	",
            "	jiz	",
            "	jizm	",
            "	jizz	",
            "	jizzed	",
            "	junkie	",
            "	junky	",
            "	kike	",
            "	kikes	",
            "	kill	",
            "	kinky	",
            "	kkk	",
            "	klan	",
            "	knobend	",
            "	kooch	",
            "	kooches	",
            "	kootch	",
            "	kraut	",
            "	kyke	",
            "	labia	",
            "	lech	",
            "	leper	",
            "	lesbians	",
            "	lesbo	",
            "	lesbos	",
            "	lez	",
            "	lezbian	",
            "	lezbians	",
            "	lezbo	",
            "	lezbos	",
            "	lezzie	",
            "	lezzies	",
            "	lezzy	",
            "	lmao	",
            "	lmfao	",
            "	loin	",
            "	loins	",
            "	lube	",
            "	lusty	",
            "	mams	",
            "	massa	",
            "	masterbate	",
            "	masterbating	",
            "	masterbation	",
            "	masturbate	",
            "	masturbating	",
            "	masturbation	",
            "	maxi	",
            "	menses	",
            "	menstruate	",
            "	menstruation	",
            "	meth	",
            "	m-fucking	",
            "	mofo	",
            "	molest	",
            "	moolie	",
            "	moron	",
            "	motherfucka	",
            "	motherfucker	",
            "	motherfucking	",
            "	mtherfucker	",
            "	mthrfucker	",
            "	mthrfucking	",
            "	muff	",
            "	muffdiver	",
            "	murder	",
            "	muthafuckaz	",
            "	muthafucker	",
            "	mutherfucker	",
            "	mutherfucking	",
            "	muthrfucking	",
            "	nad	",
            "	nads	",
            "	naked	",
            "	napalm	",
            "	nappy	",
            "	nazi	",
            "	nazism	",
            "	negro	",
            "	nigga	",
            "	niggah	",
            "	niggas	",
            "	niggaz	",
            "	nigger	",
            "	nigger	",
            "	niggers	",
            "	niggle	",
            "	niglet	",
            "	nimrod	",
            "	ninny	",
            "	nipple	",
            "	nooky	",
            "	nympho	",
            "	opiate	",
            "	opium	",
            "	oral	",
            "	orally	",
            "	organ	",
            "	orgasm	",
            "	orgasmic	",
            "	orgies	",
            "	orgy	",
            "	ovary	",
            "	ovum	",
            "	ovums	",
            "	p.u.s.s.y.	",
            "	paddy	",
            "	paki	",
            "	pantie	",
            "	panties	",
            "	panty	",
            "	pastie	",
            "	pasty	",
            "	pcp	",
            "	pecker	",
            "	pedo	",
            "	pedophile	",
            "	pedophilia	",
            "	pedophiliac	",
            "	pee	",
            "	peepee	",
            "	penetrate	",
            "	penetration	",
            "	penial	",
            "	penile	",
            "	penis	",
            "	perversion	",
            "	peyote	",
            "	phalli	",
            "	phallic	",
            "	phuck	",
            "	pillowbiter	",
            "	pimp	",
            "	pinko	",
            "	piss	",
            "	pissed	",
            "	pissoff	",
            "	piss-off	",
            "	pms	",
            "	polack	",
            "	pollock	",
            "	poon	",
            "	poontang	",
            "	porn	",
            "	porno	",
            "	pornography	",
            "	pot	",
            "	potty	",
            "	prick	",
            "	prig	",
            "	prostitute	",
            "	prude	",
            "	pube	",
            "	pubic	",
            "	pubis	",
            "	punkass	",
            "	punky	",
            "	puss	",
            "	pussies	",
            "	pussy	",
            "	pussypounder	",
            "	puto	",
            "	queaf	",
            "	queef	",
            "	queef	",
            "	queer	",
            "	queero	",
            "	queers	",
            "	quicky	",
            "	quim	",
            "	racy	",
            "	rape	",
            "	raped	",
            "	raper	",
            "	rapist	",
            "	raunch	",
            "	rectal	",
            "	rectum	",
            "	rectus	",
            "	reefer	",
            "	reetard	",
            "	reich	",
            "	retard	",
            "	retarded	",
            "	revue	",
            "	rimjob	",
            "	ritard	",
            "	rtard	",
            "	r-tard	",
            "	rum	",
            "	rump	",
            "	rumprammer	",
            "	ruski	",
            "	s.h.i.t.	",
            "	s.o.b.	",
            "	s0b	",
            "	sadism	",
            "	sadist	",
            "	scag	",
            "	scantily	",
            "	schizo	",
            "	schlong	",
            "	screw	",
            "	screwed	",
            "	scrog	",
            "	scrot	",
            "	scrote	",
            "	scrotum	",
            "	scrud	",
            "	scum	",
            "	seaman	",
            "	seamen	",
            "	seduce	",
            "	semen	",
            "	sex	",
            "	sexual	",
            "	sh1t	",
            "	s-h-1-t	",
            "	shamedame	",
            "	shit	",
            "	s-h-i-t	",
            "	shite	",
            "	shiteater	",
            "	shitface	",
            "	shithead	",
            "	shithole	",
            "	shithouse	",
            "	shits	",
            "	shitt	",
            "	shitted	",
            "	shitter	",
            "	shitty	",
            "	shiz	",
            "	sissy	",
            "	skag	",
            "	skank	",
            "	slave	",
            "	sleaze	",
            "	sleazy	",
            "	slut	",
            "	slutdumper	",
            "	slutkiss	",
            "	sluts	",
            "	smegma	",
            "	smut	",
            "	smutty	",
            "	snatch	",
            "	sniper	",
            "	snuff	",
            "	s-o-b	",
            "	sodom	",
            "	souse	",
            "	soused	",
            "	sperm	",
            "	spic	",
            "	spick	",
            "	spik	",
            "	spiks	",
            "	spooge	",
            "	spunk	",
            "	steamy	",
            "	stfu	",
            "	stiffy	",
            "	stoned	",
            "	strip	",
            "	stroke	",
            "	stupid	",
            "	suck	",
            "	sucked	",
            "	sucking	",
            "	sumofabiatch	",
            "	t1t	",
            "	tampon	",
            "	tard	",
            "	tawdry	",
            "	teabagging	",
            "	teat	",
            "	terd	",
            "	teste	",
            "	testee	",
            "	testes	",
            "	testicle	",
            "	testis	",
            "	thrust	",
            "	thug	",
            "	tinkle	",
            "	tit	",
            "	titfuck	",
            "	titi	",
            "	tits	",
            "	tittiefucker	",
            "	titties	",
            "	titty	",
            "	tittyfuck	",
            "	tittyfucker	",
            "	toke	",
            "	toots	",
            "	tramp	",
            "	transsexual	",
            "	trashy	",
            "	tubgirl	",
            "	turd	",
            "	tush	",
            "	twat	",
            "	twats	",
            "	ugly	",
            "	undies	",
            "	unwed	",
            "	urinal	",
            "	urine	",
            "	uterus	",
            "	uzi	",
            "	vag	",
            "	vagina	",
            "	valium	",
            "	viagra	",
            "	virgin	",
            "	vixen	",
            "	vodka	",
            "	vomit	",
            "	voyeur	",
            "	vulgar	",
            "	vulva	",
            "	wad	",
            "	wang	",
            "	wank	",
            "	wanker	",
            "	wazoo	",
            "	wedgie	",
            "	weed	",
            "	weenie	",
            "	weewee	",
            "	weiner	",
            "	weirdo	",
            "	wench	",
            "	wetback	",
            "	wh0re	",
            "	wh0reface	",
            "	whitey	",
            "	whiz	",
            "	whoralicious	",
            "	whore	",
            "	whorealicious	",
            "	whored	",
            "	whoreface	",
            "	whorehopper	",
            "	whorehouse	",
            "	whores	",
            "	whoring	",
            "	wigger	",
            "	womb	",
            "	woody	",
            "	wop	",
            "	wtf	",
            "	x-rated	",
            "	xxx	",
            "	yeasty	",
            "	yobbo	",
            "	zoophile	"};

    String[][] tags = {
            {"1", "road", "	transport", "pothole", "route", "bus-fare", "accident", "passenger", "driver", "	jam	", "traffic	", "conductor", "license"},
            {"2", "	agriculture	", "market", "seed", "farmer	", "harvest	", "monsoon	", "	milk	", "	poultry	", "	wages	", "	farming 	", "	pestisides	", "	insectisides	", "	fertilizer	"},
            {"3", "	business	",
                    "	marketing	",
                    "	tax	",
                    "	employee	",
                    "	salary	",
                    "	workplace	",
                    "	customer	",
                    "	client	",
                    "	vacency	",
                    "	empowerment	",
                    "	employment	"},
            {"4", "	education	",
                    "	slc	",
                    "	children	",
                    "	grades	",
                    "	college	",
                    "	career	",
                    "	school	",
                    "	books 	",
                    "	material	",
                    "	fee	",
                    "	student	",
                    "	teacher	",
                    "	university	",
                    "	student-union	",
                    "	library	",
                    "	classroom	",
                    "	admission	"},
            {"5", "	light	",
                    "	loadsheding	",
                    "	electricity	",
                    "	hydropower	",
                    "	watt	",
                    "	KW	",
                    "	MW	",
                    "	short-circuit	",
                    "	shock	"},
            {"6", "	food	",
                    "	nutrition	",
                    "	malnutrition	",
                    "	food-poision	",
                    "	quality	",
                    "	decay	",
                    "	price-hike	",
                    "	black-market	",
                    "	loot	"},
            {"7", "	law	", "police", "traffic", "theft",
                    "	eve-teasing	",
                    "	murder	",
                    "	fine	",
                    "	crime	",
                    "	murder	",
                    "	law	",
                    "	order	",
                    "	punish	"},
            {"8", "	monsoon	",
                    "	scarcity	",
                    "	pool	",
                    "	morality	"},
            {"9", "	fake_promises	",
                    "	poverty	",
                    "	corruption	",
                    "	social_issues	",
                    "	peace	"},
            {"10", "	rape	",
                    "	eve-teasing	",
                    "	domestic	",
                    "	violence	",
                    "	trafficking	"}};




    String[][] category = {{"1", "Road Department"},
            {"2", "Agricultural Development"},
            {"3", "Trade and Business"},
            {"4", "Education"},
            {"5", "Electricity"},
            {"6", "Food"},
            {"7", "Law"},
            {"8", "Federal Affairs and Local Development"},
            {"9", "Parliamentary Affairs"},
            {"10", "Women, Children and Social Welfare"}
    };

    String[] tagss =
            {"road","education","slc","children","food","market","light","loadsheding",
                    "monsoon","scarcity","grades","college","career	","transport",
                    "fake_promises	","medicines","	health	","hospital","school",
                    "water","poverty","pothole","pool","corruption","social_issues",
                    "peace","crime","loot"};

    String str_title, str_desc, str_location, str_date, str_tag, str_depart;
    Button postButton;


    String[] stopword = {"a", "	about", "	above	", "	after	", "	again	", "	against	", "	all	",
            "	am	", "	an	", "	and	", "	any	", "	are	", "	aren't	", "	as	",
            "	at	", "	be	", "	because	", "	been	", "	before	", "	being	",
            "	below	", "	between	", "	both	", "	but	", "	by	", "	can't	",
            "	cannot	", "	could	", "	couldn't	", "	did	", "	didn't	", "	do	",
            "	does	", "	doesn't	", "	doing	", "	don't	", "	down	", "	during	",
            "	each	", "	few	",
            "	for	", "	from	", "	further	", "	had	",
            "	hadn't	", "	has	", "	hasn't	", "	have	",
            "	haven't	", "	having	", "	he	", "	he'd	",
            "	he'll	", "	he's	", "	her	", "	here	",
            "	here's	", "	hers	",
            "	herself	", "	him	",
            "	himself	",
            "	his	",
            "	how	",
            "	how's	",
            "	i	",
            "	i'd	",
            "	i'll	",
            "	i'm	",
            "	i've	",
            "	if	",
            "	in	",
            "	into	",
            "	is	",
            "	isn't	",
            "	it	",
            "	it's	",
            "	its	",
            "	itself	",
            "	let's	",
            "	me	",
            "	more	",
            "	most	",
            "	mustn't	",
            "	my	",
            "	myself	",
            "	no	",
            "	nor	",
            "	not	",
            "	of	",
            "	off	",
            "	on	",
            "	once	",
            "	only	",
            "	or	",
            "	other	",
            "	ought	",
            "	our	",
            "	ours	",
            "	ourselves	",
            "	out	",
            "	over	",
            "	own	",
            "	same	",
            "	shan't	",
            "	she	",
            "	she'd	",
            "	she'll	",
            "	she's	",
            "	should	",
            "	shouldn't	",
            "	so	",
            "	some	",
            "	such	",
            "	than	",
            "	that	",
            "	that's	",
            "	the	",
            "	their	",
            "	theirs	",
            "	them	",
            "	themselves	",
            "	then	",
            "	there	",
            "	there's	",
            "	these	",
            "	they	",
            "	they'd	",
            "	they'll	",
            "	they're	",
            "	they've	",
            "	this	",
            "	those	",
            "	through	",
            "	to	",
            "	too	",
            "	under	",
            "	until	",
            "	up	",
            "	very	",
            "	was	",
            "	wasn't	",
            "	we	",
            "	we'd	",
            "	we'll	",
            "	we're	",
            "	we've	",
            "	were	",
            "	weren't	",
            "	what	",
            "	what's	",
            "	when	",
            "	when's	",
            "	where	",
            "	where's	",
            "	which	",
            "	while	",
            "	who	",
            "	who's	",
            "	whom	",
            "	why	",
            "	why's	",
            "	with	",
            "	won't	",
            "	would	",
            "	wouldn't	",
            "	you	",
            "	you'd	",
            "	you'll	",
            "	you're	",
            "	you've	",
            "	your	",
            "	yours	",
            "	yourself	",
            "	yourselves	",
            "	! ", ",", "&"};


    public PostProblem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_problem, container, false);
        sharedPreferences = getContext().getSharedPreferences(LoginFragment.MyPref, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("Username", null);
        //Toast.makeText(getContext(),username,Toast.LENGTH_LONG).show();
        title = (EditText) view.findViewById(R.id.title);
        description = (EditText) view.findViewById(R.id.desc);
        //date=(TextView)view.findViewById(R.id.date);
        //timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        //date.setText(timeStamp);
        location = (Spinner) view.findViewById(R.id.spinner4);
        //depart=(Spinner)view.findViewById(R.id.spinner5);
        str_location = String.valueOf(location.getSelectedItem());

        multi=(MultiAutoCompleteTextView)view.findViewById(R.id.multiAutoCompleteTextView1);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,tagss);

        multi.setAdapter(adapter);
        multi.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

       /* str_title = title.getText().toString();
        str_desc = description.getText().toString();
        str_location = String.valueOf(location.getSelectedItem());
        //str_depart = String.valueOf(location.getSelectedItem());
        //str_date=date.getText().toString();
        //str_tag=multi.getText().toString();

 */


        //finally ar is the arrays of tags from problem.








        postButton = (Button) view.findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPost();
            }
        });

        return view;
    }

    public void processPost() {

        BackgroundWorker bck = new BackgroundWorker();
        bck.execute();


    }

    public class BackgroundWorker extends AsyncTask<String, Void, String> {

        String result;
        String reg_url = "http://192.168.56.1/deerthon/post_problem.php";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            EditText etitle=(EditText)getActivity().findViewById(R.id.title);
            EditText edesc=(EditText)getActivity().findViewById(R.id.desc);
            Spinner locaton=(Spinner)getActivity().findViewById(R.id.spinner4);
            str_title=etitle.getText().toString();
            str_desc=edesc.getText().toString();
            str_location=String.valueOf(locaton.getSelectedItem());
            String prob = title + " " + description;

            int bad_user = 0;

            ArrayList<String> ar = new ArrayList<String>();


	/*	for ( String s : stopword) {
            s=s.trim();
			}

		for ( String bw : badWords) {
			bw=bw.trim();
			}*/
            int sw = 0;

            String[] arr = prob.split(" ");
            for (String ss : arr) {
                ss = ss.toLowerCase();
                ss = ss.replaceAll("[^\\w\\s]", "");
                for (String bw : badWords) {
                    bw = bw.trim();
                    if (ss.equals(bw)) {
                        bad_user = 1;
                        //System.out.println(" User please DO NOT USE INAPPROPRIATE word");
                        AlertDialog alert=new AlertDialog.Builder(getContext()).create();
                        alert.setTitle("Warning");
                        alert.setMessage("User please DO NOT USE INAPPROPRIATE word");
                        alert.show();
                        startActivity(new Intent(getContext(),UserIndexActivity.class));
                        //break;
                    }
                }

                if (bad_user == 1)
                    startActivity(new Intent(getContext(),UserIndexActivity.class));


                for (String s : stopword) {
                    s = s.trim();
                    if (ss.equals(s)) {
                        //System.out.println(ss);
                        sw = 1;
                    }

                }


                if (sw == 0)
                    ar.add(ss);
                sw = 0;


            }
            int i, j;
            ArrayList<String> sug_dep = new ArrayList<String>();
            for (String prtag : ar) {
                for (i = 0; i < tags.length; i++) {
                    for (j = 1; j < tags[i].length; j++) {
                        tags[i][j] = tags[i][j].trim();
                        if (prtag.equals(tags[i][j])) {
                            if (!sug_dep.contains(tags[i][0]))
                                sug_dep.add(tags[i][0]);


                        }
                    }
                }
            }
            ArrayList<String> sug_cat = new ArrayList<String>();

            for (String dep : sug_dep) {
                for (i = 0; i < category.length; i++) {
                    if (dep.equals(category[i][0])) {
                        sug_cat.add(category[i][1]);
                        //System.out.println(category[i][1]);
                        temp+=category[i][1]+"\t";
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("success")) {
                //startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                //Intent intent = new Intent(getContext(), UserIndexActivity.class);
                //startActivity(intent);
                AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Suggesstion");
                //alertDialog.setMessage(temp);
                alertDialog.show();
                Intent intent = new Intent(getContext(), UserIndexActivity.class);
                startActivity(intent);
            } else {
                AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                alert.setTitle("Message");
                //alert.setIcon(R.drawable.logo);
                alert.setMessage(s);
                alert.show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(str_title, "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(str_desc, "UTF-8") + "&" +

                        URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(str_location, "UTF-8") + "&" +

                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                        + "&" +

                        URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(temp, "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                //res = result;
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }


}
