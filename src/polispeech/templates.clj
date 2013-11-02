(ns polispeech.templates
    (:use [words.template :only [deftemplate]]))

(deftemplate speech-intro [:address :follow-up :loaded-question]
    :address ["My " {:mainstream "trusted" :else "fellow"}
                {:mainstream "citizens"
                :radical "brothers and sisters in solidarity"
                :else "Americans"} ", " ]
    :follow-up [ (hash-set "2" "6") (hash-set "years" "weeks") "ago today," :enemy :did-bad-thing "."]
        :enemy {
            :mainstream (hash-set "my opponent" :other-party)
            :radical (hash-set "the 1%" "our capitalist oppressors")
            :else :other-party
        }
            :other-party "the other party"
        :did-bad-thing [:cause "that " :effect ]
            :cause {
                :mainstream (hash-set "signed a bill" "won a primary election" )
                :radical (hash-set "issued an edict" "hatched a plot")
                :else "did something"
            }
            :effect {
                :mainstream "changed the face of our country"
                :radical ["destroyed" (hash-set "your wages" "your future")]
                :else "changed everything"
            }
    :loaded-question [ (hash-set ["Did you know " :upsetting-fact]
                        ["How long will " :be-passive]) "?"]
        :upsetting-fact {
            :mainstream ["this nation "
                            "exports fewer horse-drawn carraiges now than"
                            (hash-set "at any point in history" "in 1969")]

            :radical ["our" :unfortunate-circumstance
                        "is a direct result of" :evil-institution]
            :else "this"

        }
            :unfortunate-circumstance (hash-set [(hash-set "crippling" "degrading")
                                            (hash-set "unemployment" "oppression")]
                                        "having to expend effort to survive")
            :evil-institution (hash-set "the Patriarchy" "modern civilization" "the 1%")
        :be-passive {
            :mainstream "they continue to vote against the middle class"
            :radical (hash-set [ "you continue to be robbed of your"
                        (hash-set "freedoms" "dignity" "marijuana")]
                        "you stand by in blissful ignorance with the rest of the sheeple")
            :else "they do nothing"
        }
)

(deftemplate speech-closing [""])

(deftemplate political-speech
    (map #(identity ["\t" %])
        [:introduction "\n" :problem  "\n" :take-action "\n" :closing])
        :introduction speech-intro
        :problem [:lead-in "." :blame-game "." :indignant-statement
                {:mainstream "." :radical "!"}]
            :lead-in [ {:mainstream #{"These days, " :as-know}
                        :radical :as-know} :new-fact]
                :new-fact {
                    ;if this could be localized, that would be great
                    :mainstream ["times are tough for"
                                (hash-set "all Americans" "middle-class folks")]
                    :radical ["the corporations have"
                                (hash-set "defiled" "repressed")
                                (hash-set "mother nature" "the workers") "for too long"]
                }
                :as-know ["As " (hash-set "you" "we all") "know, "]
            :blame-game {
                :mainstream ["In fact, the economic problems caused by"
                    (hash-set :foreigners :bankers :politicians)
                    "still continue to make life difficult today"]

                :radical ["While you have struggled "
                    (hash-set "to make use of your sociology degrees"
                        "under the heel of your employers"
                        "for dignity in a capitalist society") ","
                    (hash-set :bankers :politicians)
                    " have grown fat and rich"
                ]
            }
                :foreigners [(hash-set "Chinese" "Mexican" "Arab") "immigrants"]
                :bankers [{:mainstream "irresponsible" :radical "greedy"} "bankers"]
                :politicians [(hash-set "Republican" "Democratic") "politicians"]
            :indignant-statement {
                :mainstream [ "It's time for real changes to be made,"
                            "changes that will help " :who :do-something]
                :radical ["The time has come to " (hash-set "rise up" "mobilize") "and"
                        :do-something-radical ]
            }
                :who ["the" :mainstream-heroes
                     "of this country"]
                    :mainstream-heroes [(hash-set "hard-working" "honest")
                                        (hash-set "families" "workers" "teachers")]
                :do-something (hash-set "make a decent living" "restore our global competitiveness")
                :do-something-radical ["take back our" (hash-set "factories" "cities" "society")]

        :take-action [:acknowledge-now :offer-hope :stir-the-pot]
            :acknowledge-now [(hash-set "Now" "Currently") ", we all "
                            (hash-set "know" "are aware") "of the"
                            {
                                :mainstream [(hash-set "sacrifices" "hard choices")
                                "that have to be made to keep this country together"]
                                :radical (hash-set ["crimes of " :villains]
                                                ["struggles of " :heroes])
                            }
            "."]
                        :villains ["the" (hash-set "bourgeois" :bankers)
                                "and their all-consuming decadence"]
                        :heroes ["the "(hash-set "wage" "debt") "`-slave " (hash-set "proletariat" "students")
                                "and their hopeless victimhood"]
            :offer-hope {
                :mainstream ["However, we have faced "
                            (hash-set "hard" "difficult") (hash-set "times" "challenges")
                            " before, and have always" :perservered "."]
                :radical ["But there is unrest among the people, "
                "hope in the" (hash-set "universities" "factories" "unions") "!"
                "The " (hash-set "intellectuals" "senators" "workers") "are " :getting-info "!"]
            }
                            :perservered [
                            (hash-set "pulled through" "survived")
                            "due to " (hash-set ["massive expansions of your favorite"
                                            "social programs"]
                                            "our unshakeable national unity"
                                            "the guiding hand of a strong leader")
                            ]
                            :getting-info [
                                (hash-set "waking up to" "studying")
                                "the " (hash-set "philosophy" "lifestyle") "of"
                                (hash-set "anarcho" ["post" (hash-set "`modern" "`-structural")])
                                (hash-set "chomsky" "dada" "social") "`ism"
                            ]
            :stir-the-pot [:lead-in-closing :more-upsetting-facts ]
                :lead-in-closing [{:mainstream "Citizens" :radical "Comrades"}
                        ", the time " (hash-set "is now" "has never been better"
                            "is quickly approaching") "for" :useless-action "!"]
                        :useless-action {
                            :mainstream (hash-set "vigorous letter-writing campaigns"
                                            "getting out the vote"
                                            "doing your part")
                            :radical (hash-set "organizing a general strike"
                                            "spreading the ideals of the revolution"
                                            "staging a protest")
                        }
                :more-upsetting-facts ["While " :enemy {:mainstream "gets votes and supporters"
                            :radical :bad-radical-actions} ", the situation gets worse for "
                            {:mainstream :mainstream-heroes :radical :heroes}
                            "every day."]
                        :bad-radical-actions (hash-set "exploits your labor"
                                                "saddles you with debt"
                                                "earns more money than you")
        :closing speech-closing
)
