package com.mindtree.queryBuilder;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

public class WineQueryBuilder {

	public static QueryBuilder searchConditionFiltration(String... input) {
		QueryBuilder searchTerm, wine, wineType1, wineType2, flavour, region;

		// condition for searchTerm
		if (input[0] != null && input[0] != "") {
			System.out.println(input[0]);
			searchTerm = QueryBuilders.matchQuery("_all", input[0]);
			// condition for wine
			if (input[1] != null && input[1] != "") {
				wine = createMatch("P_Winery.keyword", input[1]);
				// condition for winetype1
				if (input[2] != null && input[2] != "") {
					wineType1 = createMatch("P_WineType1.keyword", input[2]);
					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch6(region, flavour, wineType2, wineType1, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch5(flavour, wineType2, wineType1, wine, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch5(region, wineType2, wineType1, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch4(wineType2, wineType1, wine, searchTerm);
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch5(region, flavour, wineType1, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch4(flavour, wineType1, wine, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, wineType1, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch3(wineType1, wine, searchTerm);
							}

						}
					}
				} else {
					// else for winetype1 condition
					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);
						boolQueryMatch3(wineType2, wine, searchTerm);

						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch5(region, flavour, wineType2, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch4(flavour, wineType2, wine, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, wineType2, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch3(wineType2, wine, searchTerm);
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, flavour, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch3(flavour, wine, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, wine, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch2(wine, searchTerm);
							}

						}
					}

				}
			} else {
				// else for winecondition
				// condition for winetype1
				if (input[2] != null && input[2] != "") {
					wineType1 = createMatch("P_WineType1.keyword", input[2]);

					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);

						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch5(region, flavour, wineType2, wineType1, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch4(flavour, wineType2, wineType1, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, wineType2, wineType1, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch3(wineType2, wineType1, searchTerm);
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, flavour, wineType1, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch3(flavour, wineType1, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, wineType1, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch2(wineType1, searchTerm);
							}

						}
					}
				} else {
					// else for winetype1 condition
					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);

						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, flavour, wineType2, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch3(flavour, wineType2, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, wineType2, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch2(wineType2, searchTerm);
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, flavour, searchTerm);
							} else {
								// else for region condition
								return boolQueryMatch2(flavour, searchTerm);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch2(region, searchTerm);
							} else {
								// else for region condition
								return searchTerm;
							}

						}
					}

				}

			}
		} else {
			// else for searchCondition
			// condition for winery
			if (input[1] != null && input[1] != "") {
				wine = createMatch("P_Winery.keyword", input[1]);

				// condition for winetype1
				if (input[2] != null && input[2] != "") {
					wineType1 = createMatch("P_WineType1.keyword", input[2]);

					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);

						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch5(region, flavour, wineType2, wineType1, wine);
							} else {
								// else for region condition
								return boolQueryMatch4(flavour, wineType2, wineType1, wine);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, wineType2, wineType1, wine);
							} else {
								// else for region condition
								return boolQueryMatch3(wineType2, wineType1, wine);
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, flavour, wineType1, wine);
							} else {
								// else for region condition
								return boolQueryMatch3(flavour, wineType1, wine);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, wineType1, wine);
							} else {
								// else for region condition
								return boolQueryMatch2(wineType1, wine);
							}

						}
					}
				} else {
					// else for winetype1 condition
					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);

						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, flavour, wineType2, wine);
							} else {
								// else for region condition
								return boolQueryMatch3(flavour, wineType2, wine);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, wineType2, wine);
							} else {
								// else for region condition
								return boolQueryMatch2(wineType2, wine);
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, flavour, wine);
							} else {
								// else for region condition
								return boolQueryMatch2(flavour, wine);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch2(region, wine);
							} else {
								// else for region condition
								return wine;
							}

						}
					}

				}
			} else {
				// else for winecondition
				// condition for winetype1
				if (input[2] != null && input[2] != "") {
					wineType1 = createMatch("P_WineType1.keyword", input[2]);

					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);

						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch4(region, flavour, wineType2, wineType1);
							} else {
								// else for region condition
								return boolQueryMatch3(flavour, wineType2, wineType1);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, wineType2, wineType1);
							} else {
								// else for region condition
								return boolQueryMatch2(wineType2, wineType1);
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, flavour, wineType1);
							} else {
								// else for region condition
								return boolQueryMatch2(flavour, wineType1);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch2(region, wineType1);
							} else {
								// else for region condition
								return wineType1;
							}

						}
					}
				} else {
					// else for winetype1 condition
					// winetype2 condition
					if (input[3] != null && input[3] != "") {
						wineType2 = createMatch("P_WineType2.keyword", input[3]);

						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch3(region, flavour, wineType2);
							} else {
								// else for region condition
								return boolQueryMatch2(flavour, wineType2);
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch2(region, wineType2);
							} else {
								// else for region condition
								return wineType2;
							}

						}
					} else {
						// else for winetype2 condition
						// flavour condition
						if (input[4] != null && input[4] != "") {
							flavour = createMatch("P_Flavors.keyword", input[4]);

							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return boolQueryMatch2(region, flavour);
							} else {
								// else for region condition
								return flavour;
							}
						} else {
							// else for flavour condition
							// region condition
							if (input[5] != null && input[5] != "") {
								region = createMatch("P_Region.keyword", input[5]);
								return region;
							} else {
								// else for region condition
								return createMatchAll();
							}

						}
					}

				}

			}
		}

	}

	public static QueryBuilder createMatchAll() {
		return QueryBuilders.matchAllQuery();
	}

	public static QueryBuilder createMatch(String field, String match) {
		return QueryBuilders.matchQuery(field, match);
	}

	public static QueryBuilder boolQueryMatch2(QueryBuilder m1, QueryBuilder m2) {
		return QueryBuilders.boolQuery().must(m1).must(m2);
	}

	public static QueryBuilder boolQueryMatch3(QueryBuilder m1, QueryBuilder m2, QueryBuilder m3) {
		return QueryBuilders.boolQuery().must(m1).must(m2).must(m3);
	}

	public static QueryBuilder boolQueryMatch4(QueryBuilder m1, QueryBuilder m2, QueryBuilder m3, QueryBuilder m4) {
		return QueryBuilders.boolQuery().must(m1).must(m2).must(m3).must(m4);
	}

	public static QueryBuilder boolQueryMatch5(QueryBuilder m1, QueryBuilder m2, QueryBuilder m3, QueryBuilder m4,
			QueryBuilder m5) {
		return QueryBuilders.boolQuery().must(m1).must(m2).must(m3).must(m4).must(m5);
	}

	public static QueryBuilder boolQueryMatch6(QueryBuilder m1, QueryBuilder m2, QueryBuilder m3, QueryBuilder m4,
			QueryBuilder m5, QueryBuilder m6) {
		return QueryBuilders.boolQuery().must(m1).must(m2).must(m3).must(m4).must(m5).must(m6);
	}
}
